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
import org.btkj.config.pojo.param.AreaEditParam;
import org.btkj.config.pojo.param.InsurerEditParam;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.PrivilegeMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.exception.BusinessException;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
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
	public Result<Void> insurerEdit(InsurerEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			Insurer insurer = EntityGenerator.newInsurer(param);
			try {
				insurerMapper.insert(insurer);
				return Consts.RESULT.OK;
			} catch (DuplicateKeyException e) {
				return Consts.RESULT.KEY_DUPLICATED;
			}
		case UPDATE:
			insurer = insurerMapper.getByKey(param.getId());
			if (null == insurer)
				return BtkjConsts.RESULT.INSURER_NOT_EXIST;
			if (StringUtil.hasText(param.getName()))
				insurer.setName(param.getName());
			if (StringUtil.hasText(param.getIcon()))
				insurer.setIcon(param.getIcon());
			if (null != param.getBiHuId())
				insurer.setBiHuId(param.getBiHuId());
			if (null != param.getLeBaoBaId())
				insurer.setLeBaoBaId(param.getLeBaoBaId());
			insurer.setUpdated(DateUtil.currentTime());
			try {
				insurerMapper.update(insurer);
			} catch (DuplicateKeyException e) {
				return Consts.RESULT.KEY_DUPLICATED;
			}
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
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
	public Result<Void> areaEdit(AreaEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			Region region = regionMapper.getByKey(param.getCode());
			if (null == region)
				return BtkjConsts.RESULT.REGION_NOT_EXIST;
			Area area = EntityGenerator.newArea(param);
			try {
				areaMapper.insert(area);
				return Consts.RESULT.OK;
			} catch (DuplicateKeyException e) {
				return Consts.RESULT.KEY_DUPLICATED;
			}
		case UPDATE:
			area = areaMapper.getByKey(param.getCode());
			if (null == area)
				return BtkjConsts.RESULT.AREA_NOT_EXIST;
			if (null != param.getBiHuId())
				area.setBiHuId(param.getBiHuId());
			if (null != param.getRenewalPeriod())
				area.setRenewalPeriod(param.getRenewalPeriod());
			if (null != param.getPriceNoTax())
				area.setVehiclePriceNoTax(param.getPriceNoTax());
			area.setUpdated(DateUtil.currentTime());
			areaMapper.update(area);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public Map<Integer, ModularDocument> modulars(ModularType type) {
		Map<Integer, Modular> modulars = null;
		switch (type) {
		case BT:
			modulars = modularMapper.getAll();
			break;
		case APP:
		case TENANT:
			modulars = modularMapper.modulars(type);
			break;
		default:
			return null;
		}
		return CollectionUtil.isEmpty(modulars) ? null : modularDocumentFactory.build(modulars);
	}
	
	@Override
	public Map<Integer, ModularDocument> modulars(int tarId, TarType tarType, ModularType modularType) {
		Map<Integer, Modular> modulars = null;
		if (tarId > 0) {
			Map<String, Privilege> privileges = privilegeMapper.privileges(tarType, tarId);
			Set<Integer> set = new HashSet<Integer>();
			for (Privilege privilege : privileges.values()) 
				set.add(privilege.getModularId());
			modulars = modularMapper.getByKeys(set);
		} else {
			modulars = modularMapper.modulars(modularType);
			return CollectionUtil.isEmpty(modulars) ? null : modularDocumentFactory.build(modulars);
		}
		
		if (CollectionUtil.isEmpty(modulars))
			return null;
		Iterator<Modular> iterator = modulars.values().iterator();
		while (iterator.hasNext()) {
			Modular modular = iterator.next();
			if (modular.getType() != modularType.mark())
				iterator.remove();
		}
		return CollectionUtil.isEmpty(modulars) ? null : modularDocumentFactory.build(modulars);
	}
	
	@Override
	public Result<?> modularEdit(ModularEditParam param) {
		String lock = BtkjConsts.LOCKS.modularLock();
		String lockId = distributeLock.tryLock(lock);
		if (null == lockId)
			return Consts.RESULT.LOCK_CONFLICT;
		try {
			Modular modular = null;
			switch (param.getCrudType()) {
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
	public Map<Integer, Api> apis(int modularId) {
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
			switch (param.getCrudType()) {
			case CREATE:
				Api api = EntityGenerator.newApi(param);
				try {
					apiMapper.insert(api);
				} catch (DuplicateKeyException e) {
					return Consts.RESULT.KEY_DUPLICATED;
				}
				return Consts.RESULT.OK;
			case DELETE:
				api = apiMapper.getByKey(param.getId());
				if (null == api)
					return Consts.RESULT.API_NOT_EXIST;
				apiMapper.delete(api);
				return Consts.RESULT.OK;
			default:
				api = apiMapper.getByKey(param.getId());
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
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.RESULT.FAILURE;
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public void authorizeApp(int appId, Set<Integer> modulars) {
		tx.authorizeApp(appId, modulars).finish();
	}
	
	@Override
	public void authorizeAdmin(int adminId, Set<Integer> modulars) {
		tx.authorizeAdmin(adminId, modulars).finish();
	}
	
	@Override
	public void authorize(int srcId, TarType srcType, int tarId, TarType tarType, ModularType modularType, Set<Integer> modulars) {
		tx.authorize(srcId, srcType, tarId, tarType, modularType, modulars);
	}
}
