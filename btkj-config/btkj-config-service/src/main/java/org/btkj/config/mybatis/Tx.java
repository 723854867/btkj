package org.btkj.config.mybatis;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.exception.BusinessException;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tx")
public class Tx {
	
	@Resource
	private ApiMapper apiMapper;
	@Resource
	private ModularDao modularDao;
	@Resource
	private ModularMapper modularMapper;

	/**
	 * 新增模块
	 * 
	 * @param modular
	 */
	@Transactional
	public Modular modularAdd(ModularEditParam param) {
		Map<Integer, Modular> modulars = modularDao.getAll();
		Modular parent = 0 == param.getParentId() ? null : modulars.get(param.getParentId());
		if (0 != param.getParentId() && null == parent)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		Modular modular = EntityGenerator.newModular(param.getName(), parent);
		if (null != modular.getParentId() && 0 != modular.getParentId())					// 左右值重排序
			modularDao.updateForInsert(modular.getLeft(), 2);
		try {
			modularDao.insert(modular);
		} catch (DuplicateKeyException e) {
			throw new BusinessException(Code.KEY_DUPLICATED, e);
		}
		return modular;
	}
	
	@Transactional
	public Modular modularUpdate(ModularEditParam param) { 
		Modular modular = modularDao.getByKey(param.getId());
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		if (null != param.getName())
			modular.setName(param.getName());
		int parentId = param.getParentId();
		if (0 != parentId && ((null == modular.getParentId() && 0 != modular.getParentId()) || modular.getParentId() != parentId)) {
			Modular parent = modularDao.getByKey(parentId);
			if (null == parent)
				throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
			Map<Integer, Modular> children = modularDao.getChildren(modular.getLeft(), modular.getRight());
			modular.setParentId(parentId);
			int step = modular.getRight() - modular.getLeft() + 1;
			int moveStep = 0;
			if (parent.getLeft() > modular.getLeft()) {			// 将该节点移动到右边
				moveStep = parent.getRight() - modular.getRight() - 1;
				modularDao.updateForRightMove(-step, parent.getRight(), modular.getRight(), children.keySet());
			} else {
				moveStep = parent.getRight() - modular.getLeft();
				modularDao.updateForLeftMove(step, parent.getRight(), modular.getLeft(), children.keySet());
			}
			modularDao.updateForMove(moveStep, modular.getLeft(), modular.getRight(), children.keySet());
		}
		modular.setUpdated(DateUtil.currentTime());
		modularDao.update(modular);
		return modular;
	}
	
	@Transactional
	public TxCallback modularDelete(int modularId) {
		Modular modular = modularDao.getByKey(modularId);
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		if (!CollectionUtil.isEmpty(apiMapper.apis(modularId)))
			throw new BusinessException(BtkjCode.MODULAR_API_BINDED);
		Map<Integer, Modular> children = modularDao.getChildren(modular.getLeft(), modular.getRight());
		modularDao.delete(modular.getLeft(), modular.getRight());
		modularDao.updateForDelete(modular.getRight(), modular.getRight() - modular.getLeft() + 1);
		return new TxCallback() {
			@Override
			public void finish() {
				modularMapper.remove(children);
			}
		};
	}
}
