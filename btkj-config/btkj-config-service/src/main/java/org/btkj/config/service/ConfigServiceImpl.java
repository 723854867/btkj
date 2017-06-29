package org.btkj.config.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private InsurerMapper insurerMapper;
	
	@Override
	public Region getRegionById(int region) {
		return regionMapper.getByKey(region);
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
	public Insurer getInsurerById(int insurerId) {
		return insurerMapper.getByKey(insurerId);
	}
	
	@Override
	public List<Insurer> insurers(List<Integer> list) {
		return insurerMapper.getWithinKey(list);
	}
}
