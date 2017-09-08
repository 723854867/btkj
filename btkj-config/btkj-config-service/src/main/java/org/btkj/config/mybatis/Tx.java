package org.btkj.config.mybatis;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.ModularDocumentFactory;
import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.mybatis.dao.PrivilegeDao;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.PrivilegeMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.entity.config.Modular;
import org.btkj.pojo.entity.config.Privilege;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.config.ModularDocument;
import org.btkj.pojo.param.config.ModularEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.key.Pair;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
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
	private PrivilegeDao privilegeDao;
	@Resource
	private ModularMapper modularMapper;
	@Resource
	private PrivilegeMapper privilegeMapper;
	@Resource
	private ModularDocumentFactory modularDocumentFactory;
	
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
		Modular modular = EntityGenerator.newModular(param.getName(), param.getCid(), parent, param.getModularType());
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
	
	@Transactional
	public TxCallback authorize(int tarId, Set<Integer> modulars, ModularType type) {
		privilegeDao.deleteByTypeAndTarId(type.mark(), tarId);
		Set<Integer> set = CollectionUtil.isEmpty(modulars) ? null : _modularsFilter(type, modulars);
		Map<String, Privilege> privileges = CollectionUtil.isEmpty(set) ? null : EntityGenerator.newPrivileges(type, tarId, set);
		if (!CollectionUtil.isEmpty(privileges))
			privilegeDao.replace(privileges.values());
		return new TxCallback() {
			@Override
			public void finish() {
				privilegeMapper.privilegesClear(type, tarId);
				if (!CollectionUtil.isEmpty(privileges))
					privilegeMapper.flush(privileges);
			}
		};
	}
	
	private Set<Integer> _modularsFilter(ModularType type, Set<Integer> modulars) { 
		Map<Integer, Modular> map = modularMapper.modulars(type);
		Map<Integer, ModularDocument> documents = CollectionUtil.isEmpty(map) ? Collections.EMPTY_MAP : modularDocumentFactory.build(map);
		return _doFilter(type, documents, modulars);
	}
	
	private Set<Integer> _doFilter(ModularType type, Map<Integer, ModularDocument> documents, Set<Integer> modulars) {
		Map<String, Pair<Boolean, Integer>> endpoints = new HashMap<String, Pair<Boolean, Integer>>();
		_search(documents, modulars, null, endpoints, type);
		if (CollectionUtil.isEmpty(endpoints))
			return Collections.EMPTY_SET;
		Set<Integer> set = new HashSet<Integer>();
		for (Pair<Boolean, Integer> pair : endpoints.values()) {
			if (!pair.getFirst())
				continue;
			set.add(pair.getSecond());
		}
		return set;
	}
	
	private void _search(Map<Integer, ModularDocument> documents, Set<Integer> modulars, String path, Map<String, Pair<Boolean, Integer>> endpoints, ModularType type) {
		for (Entry<Integer, ModularDocument> entry : documents.entrySet()) {
			String cpath = null == path ? String.valueOf(entry.getKey()) : path + Consts.SYMBOL_UNDERLINE + String.valueOf(entry.getKey());
			boolean has = modulars.remove(entry.getKey());
			endpoints.put(cpath, has ? new Pair<Boolean, Integer>(true, entry.getKey()) : new Pair<Boolean, Integer>(false, entry.getKey()));
			if (has) {									// 设置所有的父类path都为true
				String iterPath = path;
				while (StringUtil.hasText(iterPath)) {
					if (endpoints.get(iterPath).getFirst())
						break;									// 如果父节点已经被遍历过了直接返回
					endpoints.get(iterPath).setFirst(true);		
					int idx = iterPath.lastIndexOf("_");
					iterPath = -1 == idx ? null : path.substring(0, idx);
				}
			}
			if (modulars.isEmpty())
				return;
			if (CollectionUtil.isEmpty(entry.getValue().children()))
				continue;
			_search(entry.getValue().children(), modulars, cpath, endpoints, type);
		}
	}
}
