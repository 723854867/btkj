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
	public List<Insurer> insurers(List<Integer> list) {
		return insurerMapper.getWithinKey(list);
	}
}
