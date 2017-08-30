package org.btkj.config.mybatis;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.mybatis.dao.PrivilegeDao;
import org.btkj.config.pojo.ModularDocumentFactory;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.entity.Privilege;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.PrivilegeMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.exception.BusinessException;
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
	
	@Transactional
	public TxCallback authorizeApp(int appId, Set<Integer> modulars) {
		privilegeDao.deleteByTarTypeAndTarId(TarType.APP.mark(), appId);
		Set<Integer> set = null;
		if (!CollectionUtil.isEmpty(modulars)) {
			set = _modularsFilter(ModularType.ADMIM, modulars);
			set.addAll(_modularsFilter(ModularType.EMPLOYEE, modulars));
		}
		Map<String, Privilege> privileges = CollectionUtil.isEmpty(set) ? null : EntityGenerator.newPrivileges(TarType.APP, appId, set);
		if (!CollectionUtil.isEmpty(privileges))
			privilegeDao.replace(privileges.values());
		return new TxCallback() {
			@Override
			public void finish() {
				privilegeMapper.privilegesClear(TarType.APP, appId);
				if (!CollectionUtil.isEmpty(privileges))
					privilegeMapper.flush(privileges);
			}
		};
	}
	
	@Transactional
	public TxCallback authorizeAdmin(int adminId, Set<Integer> modulars) {
		privilegeDao.deleteByTarTypeAndTarId(TarType.ADMIN.mark(), adminId);
		Set<Integer> set = CollectionUtil.isEmpty(modulars) ? null : _modularsFilter(ModularType.ADMIM, modulars);
		Map<String, Privilege> privileges = CollectionUtil.isEmpty(set) ? null : EntityGenerator.newPrivileges(TarType.ADMIN, adminId, set);
		if (!CollectionUtil.isEmpty(privileges))
			privilegeDao.replace(privileges.values());
		return new TxCallback() {
			@Override
			public void finish() {
				privilegeMapper.privilegesClear(TarType.ADMIN, adminId);
				if (!CollectionUtil.isEmpty(privileges))
					privilegeMapper.flush(privileges);
			}
		};
	}
	
	@Transactional
	public TxCallback authorize(int srcId, TarType srcType, int tarId, TarType tarType, ModularType modularType, Set<Integer> modulars) {
		privilegeDao.deleteByTarTypeAndTarId(tarType.mark(), tarId);
		Map<String, Privilege> privileges = CollectionUtil.isEmpty(modulars) ? Collections.EMPTY_MAP : privilegeMapper.privileges(srcType, srcId);
		Set<Integer> owns = new HashSet<Integer>();
		for (Privilege privilege : privileges.values())
			owns.add(privilege.getModularId());
		Set<Integer> set = _modularsFilter(modularType, modulars, owns);
		Map<String, Privilege> newPrivileges = CollectionUtil.isEmpty(set) ? Collections.EMPTY_MAP : EntityGenerator.newPrivileges(tarType, tarId, set);
		privilegeDao.replace(newPrivileges.values());
		return new TxCallback() {
			@Override
			public void finish() {
				privilegeMapper.privilegesClear(tarType, tarId);
				if (!CollectionUtil.isEmpty(newPrivileges))
					privilegeMapper.flush(newPrivileges);
			}
		};
	}
	
	private Set<Integer> _modularsFilter(ModularType type, Set<Integer> modulars) { 
		Map<Integer, Modular> map = modularMapper.modulars(type);
		Map<Integer, ModularDocument> documents = CollectionUtil.isEmpty(map) ? Collections.EMPTY_MAP : modularDocumentFactory.build(map);
		return _doFilter(type, documents, modulars);
	}
	
	private Set<Integer> _modularsFilter(ModularType type, Set<Integer> modulars, Set<Integer> owns) { 
		Map<Integer, Modular> map = modularMapper.modulars(type);
		Iterator<Integer> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			int modularId = iterator.next();
			if (!owns.remove(modularId)) {
				iterator.remove();
				modulars.remove(modularId);
			}
		}
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
