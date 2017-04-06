package org.btkj.config.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.mapper.InsurerMapper;
import org.btkj.config.redis.mapper.RegionMapper;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private InsurerMapper insuranceMapper;
	
	@Override
	public Map<Integer, Region> getRegions() {
		return regionMapper.getAll();
	}

	@Override
	public Map<Integer, Insurer> getInsurances() {
		return insuranceMapper.getAll();
	}
}
