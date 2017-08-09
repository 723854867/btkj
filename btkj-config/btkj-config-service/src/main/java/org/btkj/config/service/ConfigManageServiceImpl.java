package org.btkj.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.mybatis.EntityGenerator;
import org.btkj.config.mybatis.Tx;
import org.btkj.config.pojo.ModularDocumentFactory;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.entity.Privilege;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.config.pojo.param.ApiEditParam;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.PrivilegeMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.Region;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("configManageService")
public class ConfigManageServiceImpl implements ConfigManageService {
	
	@Resource
	private Tx tx;
	@Resource
	private ApiMapper apiMapper;
	@Resource
	private AreaMapper areaMapper;
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private ModularMapper modularMapper;
	@Resource
	private InsurerMapper insurerMapper;
	@Resource
	private DistributeLock distributeLock;
	@Resource
	private PrivilegeMapper privilegeMapper;
	@Resource
	private ModularDocumentFactory modularDocumentFactory;

	@Override
	public List<Insurer> insurers() {
		return new ArrayList<Insurer>(insurerMapper.getAll().values());
	}
	
	@Override
	public Result<Void> insurerAdd(int id, String name, String icon, boolean bindBiHu, String leBaoBaId) {
		Insurer insurer = EntityGenerator.newInsurer(id, name, icon, bindBiHu, leBaoBaId);
		try {
			insurerMapper.insert(insurer);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> insurerUpdate(int id, String name, String icon, boolean bindBiHu, String leBaoBaId) {
		Insurer insurer = insurerMapper.getByKey(id);
		if (null == insurer)
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		insurer.setName(name);
		insurer.setIcon(icon);
		insurer.setBiHuId(bindBiHu ? id : 0);
		insurer.setLeBaoBaId(leBaoBaId);
		try {
			insurerMapper.update(insurer);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Map<Integer, AreaInfo> areas() {
		Map<Integer, Area> areas = areaMapper.getAll();
		if (CollectionUtil.isEmpty(areas))
			return CollectionUtil.EMPTY_MAP;
		Set<Integer> set = new HashSet<Integer>();
		for (Area area : areas.values())
			set.add(area.getCode());
		Map<Integer, Region> regions = regionMapper.getByKeys(set);
		Map<Integer, AreaInfo> map = new HashMap<Integer, AreaInfo>();
		for (Area area : areas.values()) {
			Region region = regions.get(area.getCode());
			map.put(area.getCode(), new AreaInfo(area, region));
		}
		return map;
	}
	
	@Override
	public Result<Void> areaAdd(int code, int renewalPeriod, int biHuId, boolean priceNoTax) {
		Area area = EntityGenerator.newArea(code, renewalPeriod, biHuId, priceNoTax);
		try {
			areaMapper.insert(area);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> areaUpdate(int code, int renewalPeriod, int biHuId, boolean priceNoTax) {
		Area area = areaMapper.getByKey(code);
		if (null == area)
			return BtkjConsts.RESULT.AREA_NOT_EXIST;
		area.setBiHuId(biHuId);
		area.setRenewalPeriod(renewalPeriod);
		area.setUpdated(DateUtil.currentTime());
		area.setVehiclePriceNoTax(priceNoTax);
		areaMapper.update(area);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Map<Integer, ModularDocument> modulars(TarType type, int tarId) {
		switch (type) {
		case ADMIN:
			Map<Integer, Modular> modulars = modularMapper.modulars(ModularType.BAOTU);
			return CollectionUtil.isEmpty(modulars) ? null : modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
		case APP:
			Map<Integer, ModularDocument> documents = null;
			modulars = modularMapper.modulars(ModularType.APP);
			if (!CollectionUtil.isEmpty(modulars))
				documents = modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
			modulars = modularMapper.modulars(ModularType.TENANT);
			if (!CollectionUtil.isEmpty(modulars))
				documents.putAll(modularDocumentFactory.build(new ArrayList<Modular>(modulars.values())));
			return documents;
		case USER:
			Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.APP, tarId);
			Set<Integer> set = new HashSet<Integer>();
			for (Privilege privilege : privileges.values()) 
				set.add(privilege.getModularId());
			modulars = modularMapper.getByKeys(set);
			if (CollectionUtil.isEmpty(modulars))
				return null;
			Iterator<Modular> iterator = modulars.values().iterator();
			while (iterator.hasNext()) {
				Modular modular = iterator.next();
				if (modular.getType() == ModularType.TENANT.mark())
					iterator.remove();
			}
			return modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
		case TENANT:
			privileges = privilegeMapper.privileges(TarType.APP, tarId);
			set = new HashSet<Integer>();
			for (Privilege privilege : privileges.values()) 
				set.add(privilege.getModularId());
			modulars = modularMapper.getByKeys(set);
			if (CollectionUtil.isEmpty(modulars))
				return null;
			iterator = modulars.values().iterator();
			while (iterator.hasNext()) {
				Modular modular = iterator.next();
				if (modular.getType() == ModularType.APP.mark())
					iterator.remove();
			}
			return modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
		case EMPLOYEE:
			privileges = privilegeMapper.privileges(TarType.TENANT, tarId);
			set = new HashSet<Integer>();
			for (Privilege privilege : privileges.values()) 
				set.add(privilege.getModularId());
			modulars = modularMapper.getByKeys(set);
			return CollectionUtil.isEmpty(modulars) ? null : modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
		default:
			return null;
		}
	}
	
	@Override
	public Result<?> modularEdit(ModularEditParam param) {
		String lock = BtkjConsts.LOCKS.modularLock();
		String lockId = distributeLock.tryLock(lock);
		if (null == lockId)
			return Consts.RESULT.LOCK_CONFLICT;
		try {
			Modular modular = null;
			switch (param.getType()) {
			case CREATE:
				modular = tx.modularAdd(param);
				modularMapper.flush(modular);
				return Result.result(Code.OK, modular.getId());
			case DELETE:
				tx.modularDelete(param.getId()).finish();
				return Consts.RESULT.OK;
			default:
				modular = tx.modularUpdate(param);
				modularMapper.flush(modular);
				return Consts.RESULT.OK;
			}
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public Map<String, Api> apis(int modularId) {
		return apiMapper.apis(modularId);
	}
	
	@Override
	public Result<Void> apiEdit(ApiEditParam param) {
		String lock = BtkjConsts.LOCKS.modularLock();
		String lockId = distributeLock.tryLock(lock);
		if (null == lockId)
			return Consts.RESULT.LOCK_CONFLICT;
		try {
			if (null != param.getModularId() && null == modularMapper.getByKey(param.getModularId()))
				return BtkjConsts.RESULT.MODULAR_NOT_EXIST;
			switch (param.getType()) {
			case CREATE:
				Api api = EntityGenerator.newApi(param);
				try {
					apiMapper.insert(api);
				} catch (DuplicateKeyException e) {
					return Consts.RESULT.KEY_DUPLICATED;
				}
				return Consts.RESULT.OK;
			case DELETE:
				api = apiMapper.getByKey(param.getPkg());
				if (null == api)
					return Consts.RESULT.API_NOT_EXIST;
				apiMapper.delete(api);
				return Consts.RESULT.OK;
			default:
				api = apiMapper.getByKey(param.getPkg());
				if (null == api)
					return Consts.RESULT.API_NOT_EXIST;
				if (null != param.getModularId())
					api.setModularId(param.getModularId());
				if (null != param.getName())
					api.setName(param.getName());
				api.setUpdated(DateUtil.currentTime());
				apiMapper.update(api);
				return Consts.RESULT.OK;
			}
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public Result<Void> authorizeApp(int appId, Set<Integer> modulars) {
		privilegeMapper.deletePrivileges(TarType.APP, appId);
		if (!CollectionUtil.isEmpty(modulars)) {
			Map<Integer, Modular> map = modularMapper.getByKeys(modulars);
			if (modulars.size() != modulars.size())
				return Consts.RESULT.FORBID;
			for (Modular modular : map.values()) {
				if (modular.getType() != ModularType.APP.mark() || modular.getType() != ModularType.TENANT.mark())	// 只能给平台授权平台和商户模块的权限
					return Consts.RESULT.FORBID;
			}
			privilegeMapper.replace(EntityGenerator.newPrivileges(TarType.APP, appId, modulars));
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> authorizeAdmin(int adminId, Set<Integer> modulars) {
		privilegeMapper.deletePrivileges(TarType.ADMIN, adminId);
		if (!CollectionUtil.isEmpty(modulars)) {
			Map<Integer, Modular> map = modularMapper.getByKeys(modulars);
			if (modulars.size() != modulars.size())
				return Consts.RESULT.FORBID;
			for (Modular modular : map.values()) {
				if (modular.getType() != ModularType.BAOTU.mark())	// 只能给平台授权平台和商户模块的权限
					return Consts.RESULT.FORBID;
			}
			privilegeMapper.replace(EntityGenerator.newPrivileges(TarType.APP, adminId, modulars));
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> authorizeUser(int appId, int uid, Set<Integer> modulars) {
		privilegeMapper.deletePrivileges(TarType.USER, uid);
		if (!CollectionUtil.isEmpty(modulars)) {
			Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.APP, appId);
			a : for (int modularId : modulars) {
				for (Privilege privilege : privileges.values()) {
					if (privilege.getModularId() == modularId)
						continue a;
				}
				return Consts.RESULT.FORBID;
			}
			Map<Integer, Modular> map = modularMapper.getByKeys(modulars);
			if (modulars.size() != modulars.size())
				return Consts.RESULT.FORBID;
			for (Modular modular : map.values()) {
				if (modular.getType() != ModularType.APP.mark())
					return Consts.RESULT.FORBID;
			}
			privilegeMapper.replace(EntityGenerator.newPrivileges(TarType.USER, uid, modulars));
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> authorizeTenant(int appId, int tid, Set<Integer> modulars) {
		privilegeMapper.deletePrivileges(TarType.TENANT, tid);
		if (!CollectionUtil.isEmpty(modulars)) {
			Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.APP, appId);
			a : for (int modularId : modulars) {
				for (Privilege privilege : privileges.values()) {
					if (privilege.getModularId() == modularId)
						continue a;
				}
				return Consts.RESULT.FORBID;
			}
			Map<Integer, Modular> map = modularMapper.getByKeys(modulars);
			if (modulars.size() != modulars.size())
				return Consts.RESULT.FORBID;
			for (Modular modular : map.values()) {
				if (modular.getType() != ModularType.TENANT.mark())
					return Consts.RESULT.FORBID;
			}
			privilegeMapper.replace(EntityGenerator.newPrivileges(TarType.TENANT, tid, modulars));
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> authorizeEmployee(int tid, int employeeId, Set<Integer> modulars) {
		privilegeMapper.deletePrivileges(TarType.EMPLOYEE, employeeId);
		if (!CollectionUtil.isEmpty(modulars)) {
			Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.EMPLOYEE, employeeId);
			a : for (int modularId : modulars) {
				for (Privilege privilege : privileges.values()) {
					if (privilege.getModularId() == modularId)
						continue a;
				}
				return Consts.RESULT.FORBID;
			}
			Map<Integer, Modular> map = modularMapper.getByKeys(modulars);
			if (modulars.size() != modulars.size())
				return Consts.RESULT.FORBID;
			for (Modular modular : map.values()) {
				if (modular.getType() != ModularType.TENANT.mark())
					return Consts.RESULT.FORBID;
			}
			privilegeMapper.replace(EntityGenerator.newPrivileges(TarType.EMPLOYEE, employeeId, modulars));
		}
		return Consts.RESULT.OK;
	}
}
