package org.btkj.config.mybatis;

import javax.annotation.Resource;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ModularMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.exception.BusinessException;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tx")
public class Tx {
	
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
		Modular parent = null == param.getParentId() ? null : modularMapper.getByKey(param.getParentId());
		if (null != param.getParentId() && null == parent)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		Modular modular = EntityGenerator.newModular(param, parent);
		if (null != modular.getParentId())					// 左右值重排序
			modularDao.updateForInsert(modular.getLeft(), 2);
		modularDao.insert(modular);
		return modular;
	}
	
	@Transactional
	public Modular modularUpdate(ModularEditParam param) { 
		Modular modular = modularMapper.getByKey(param.getId());
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		if (null != param.getName())
			modular.setName(param.getName());
		String parentId = param.getParentId();
		if (null != parentId && (null == modular.getParentId() || !modular.getParentId().equals(parentId))) {
			Modular parent = modularMapper.getByKey(parentId);
			if (null == parent)
				throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
			modular.setParentId(parentId);
			int step = modular.getRight() - modular.getLeft() + 1;
			if (parent.getLeft() > modular.getLeft()) {			// 将该节点移动到右边
				int step1 = parent.getRight() - modular.getRight() - 1;
				modularDao.updateForRightMove(-step, step1, parent.getRight(), modular.getLeft(), modular.getRight());
			} else {
				int step1 = parent.getRight() - modular.getLeft();
				modularDao.updateForLeftMove(step, step1, parent.getLeft(), modular.getLeft(), modular.getRight());
			}
		}
		modular.setUpdated(DateUtil.currentTime());
		modularDao.update(modular);
		return modular;
	}
	
	@Transactional
	public Modular modularDelete(String id) { 
		Modular modular = modularMapper.getByKey(id);
		if (null == modular)
			throw new BusinessException(BtkjCode.MODULAR_NOT_EXIST);
		modularDao.updateForDelete(modular.getRight(), modular.getRight() - modular.getLeft() + 1);
		modularDao.delete(id);
		return modular;
	}
}
