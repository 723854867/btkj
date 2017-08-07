package org.btkj.config.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.mybatis.EntityGenerator;
import org.btkj.config.mybatis.Tx;
import org.btkj.config.pojo.ModularDocumentFactory;
import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.config.pojo.param.ApiEditParam;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.Region;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.Consts;
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
	public List<AreaInfo> areas() {
		List<Area> list = new ArrayList<Area>(areaMapper.getAll().values());
		if (CollectionUtil.isEmpty(list))
			return Collections.EMPTY_LIST;
		List<Integer> l = new ArrayList<Integer>();
		for (Area area : list)
			l.add(area.getCode());
		List<Region> regions = new ArrayList<Region>(regionMapper.getByKeys(l).values());
		List<AreaInfo> areas = new ArrayList<AreaInfo>();
		a : for (Area area : list) {
			for (Region region : regions) {
				if (region.getId() == area.getCode()) {
					areas.add(new AreaInfo(area, region));
					continue a;
				}
			}
			areas.add(new AreaInfo(area, null));
		}
		return areas;
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
	public Map<String, ModularDocument> modulars() {
		Map<String, Modular> modulars = modularMapper.getAll();
		if (CollectionUtil.isEmpty(modulars))
			return null;
		return modularDocumentFactory.build(new ArrayList<Modular>(modulars.values()));
	}
	
	@Override
	public Result<Void> modularEdit(ModularEditParam param) {
		String lock = BtkjConsts.LOCKS.modularLock();
		String lockId = distributeLock.tryLock(lock);
		if (null == lockId)
			return Consts.RESULT.LOCK_CONFLICT;
		try {
			try {
				Modular modular = null;
				switch (param.getType()) {
				case CREATE:
					modular = tx.modularAdd(param);
					modularMapper.flush(modular);
					return Consts.RESULT.OK;
				default:
					modular = tx.modularUpdate(param);
					modularMapper.flush(modular);
					return Consts.RESULT.OK;
				}
			} catch (BusinessException e) {
				return Result.result(e.getCode());
			}
		} finally {
			distributeLock.unLock(lock, lockId);
		}
	}
	
	@Override
	public Map<String, Api> apis(String modularId) {
		return apiMapper.apis(modularId);
	}
	
	@Override
	public Result<Void> apiEdit(ApiEditParam param) {
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
			if (null != param.getModularId()) {
				Modular modular = modularMapper.getByKey(param.getModularId());
				if (null == modular)
					return BtkjConsts.RESULT.MODULAR_NOT_EXIST;
			}
			if (null != param.getName())
				api.setName(param.getName());
			api.setUpdated(DateUtil.currentTime());
			apiMapper.update(api);
			return Consts.RESULT.OK;
		}
	}
}
