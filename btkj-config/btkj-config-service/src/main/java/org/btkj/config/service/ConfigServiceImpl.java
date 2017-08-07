package org.btkj.config.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.Region;
import org.rapid.util.common.Consts;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
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
	
	@Override
	public Region region(int region) {
		return regionMapper.getByKey(region);
	}
	
	@Override
	public Map<Integer, Region> regions(Collection<Integer> regionIds) {
		return regionMapper.getByKeys(regionIds);
	}
	
	@Override
	public boolean isSubRegion(int region1, int region2) {
		Region r1 = regionMapper.getByKey(region1);
		Region r2 = regionMapper.getByKey(region2);
		if (null == r1 || null == r2 || r2.getLevel() < r1.getLevel())
			return false;
		if (region1 == region2 || r2.getParentId() == region1)
			return true;
		return isSubRegion(region1, r2.getParentId());
	}
	
	@Override
	public Region subordinateProvince(int region) {
		Region r = regionMapper.getByKey(region);
		if (null == r)
			return null;
		if (r.getLevel() == Consts.RegionLevel.COUNTRY.mark())
			return null;
		if (r.getLevel() == Consts.RegionLevel.PROVINCE.mark())
			return r;
		return subordinateProvince(r.getParentId());
	}
	
	@Override
	public LinkedList<Region> regionRoute(int region) {
		LinkedList<Region> list = new LinkedList<Region>();
		_regionRoute(list, region);
		return list;
	}
	
	private void _regionRoute(LinkedList<Region> regions, int region) {
		Region r = regionMapper.getByKey(region);
		if (null == r)
			return;
		regions.addFirst(r);
		if (0 == r.getParentId())
			return;
		_regionRoute(regions, r.getParentId());
	}
	
	@Override
	public Insurer getInsurerById(int insurerId) {
		return insurerMapper.getByKey(insurerId);
	}
	
	@Override
	public Map<Integer, Insurer> insurers(Collection<Integer> tids) {
		return insurerMapper.getByKeys(tids);
	}
	
	@Override
	public Area area(int areaId) {
		return areaMapper.getByKey(areaId);
	}
	
	@Override
	public boolean checkPerssion(String pkg, String mod) {
		Api api = apiMapper.getByKey(pkg);
		if (null == api)					// 如果该 api 没有参与权限配置则不需要检测权限
			return true;
		Modular modular = modularMapper.getByKey(api.getModularId());
		BigInteger modularMod = new BigInteger(modular.getId());
		return new BigInteger(mod).and(modularMod).equals(modularMod);
	}
}
