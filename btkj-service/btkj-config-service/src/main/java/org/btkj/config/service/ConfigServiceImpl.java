package org.btkj.config.service;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.entity.Region;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private RegionMapper regionMapper;
	
	@Override
	public Region getRegionById(int region) {
		return regionMapper.getByKey(region);
	}
}
