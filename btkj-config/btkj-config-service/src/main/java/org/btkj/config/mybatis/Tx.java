package org.btkj.config.mybatis;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.exception.BusinessException;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tx")
public class Tx {
	
	@Resource
	private ModularDao modularDao;

	/**
	 * 新增模块
	 * 
	 * @param modular
	 */
	@Transactional
	public Modular modularAdd(ModularEditParam param) {
		Map<String, Modular> modulars = modularDao.getAll();
		Modular parent = null == param.getParentId() ? null : modulars.get(param.getParentId());
		if (null != param.getParentId() && null == parent)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		Modular modular = EntityGenerator.newModular(_nextModularId(modulars.keySet()), param.getName(), parent);
		if (null != modular.getParentId())					// 左右值重排序
			modularDao.updateForInsert(modular.getLeft(), 2);
		try {
			modularDao.insert(modular);
		} catch (DuplicateKeyException e) {
			throw new BusinessException(Code.KEY_DUPLICATED, e);
		}
		return modular;
	}
	
	private String _nextModularId(Set<String> modulars) {
		BigInteger base = new BigInteger("2");
		int idx = 0;
		while (true) {
			String val = base.pow(idx++).toString();
			if (modulars.contains(val))
				continue;
			return val;
		}
	}
	
	@Transactional
	public Modular modularUpdate(ModularEditParam param) { 
		Modular modular = modularDao.getByKey(param.getId());
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		if (null != param.getName())
			modular.setName(param.getName());
		String parentId = param.getParentId();
		if (null != parentId && (null == modular.getParentId() || !modular.getParentId().equals(parentId))) {
			Modular parent = modularDao.getByKey(parentId);
			if (null == parent)
				throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
			Map<String, Modular> children = modularDao.getChildren(modular.getLeft(), modular.getRight());
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
	public Modular modularDelete(String id) { 
		Modular modular = modularDao.getByKey(id);
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		modularDao.updateForDelete(modular.getRight(), modular.getRight() - modular.getLeft() + 1);
		modularDao.delete(id);
		return modular;
	}
}
