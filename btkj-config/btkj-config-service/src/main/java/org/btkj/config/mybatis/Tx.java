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
		Map<Integer, Modular> modulars = modularDao.getByType(param.getModularType().mark());
		Modular parent = 0 == param.getParentId() ? null : modulars.get(param.getParentId());
		if (0 != param.getParentId() && null == parent)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		if (null != parent && parent.getType() != param.getModularType().mark())
			throw new BusinessException(BtkjCode.MODULAR_TYPE_UNMATCH);
		if (null == parent && !modulars.isEmpty())
			throw new BusinessException(BtkjCode.MODULAR_ROOT_EXIST);
		Modular modular = EntityGenerator.newModular(param.getName(), parent, param.getModularType());
		if (null != modular.getParentId() && 0 != modular.getParentId())					// 左右值重排序
			modularDao.updateForInsert(modular.getLeft(), 2, param.getModularType().mark());
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
			if (parent.getType() != modular.getType())
				throw new BusinessException(BtkjCode.MODULAR_TYPE_UNMATCH);
			Map<Integer, Modular> children = modularDao.getChildren(modular.getLeft(), modular.getRight(), modular.getType());
			modular.setParentId(parentId);
			int step = modular.getRight() - modular.getLeft() + 1;
			int moveStep = 0;
			if (parent.getLeft() > modular.getLeft()) {			// 将该节点移动到右边
				moveStep = parent.getRight() - modular.getRight() - 1;
				modularDao.updateForRightMove(-step, parent.getRight(), modular.getRight(), children.keySet(), modular.getType());
			} else {
				moveStep = parent.getRight() - modular.getLeft();
				modularDao.updateForLeftMove(step, parent.getRight(), modular.getLeft(), children.keySet(), modular.getType());
			}
			modularDao.updateForMove(moveStep, modular.getLeft(), modular.getRight(), children.keySet(), modular.getType());
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
		Map<Integer, Modular> children = modularDao.getChildren(modular.getLeft(), modular.getRight(), modular.getType());
		modularDao.delete(modular.getLeft(), modular.getRight(), modular.getType());
		modularDao.updateForDelete(modular.getRight(), modular.getRight() - modular.getLeft() + 1, modular.getType());
		return new TxCallback() {
			@Override
			public void finish() {
				modularMapper.remove(children);
			}
		};
	}
}
