package org.btkj.config.redis.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.persistence.mapper.RegionCityMapper;
import org.btkj.config.pojo.entity.RegionCity;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.redis.Redis;
import org.rapid.redis.service.RedisService;
import org.rapid.util.common.db.ProtostuffEntitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionCityRedisService extends RedisService<Integer, RegionCity> {
	
	@Resource
	private RegionCityMapper regionCityMapper;
	
	protected RegionCityRedisService() {
		super(RegionCity.class, RedisKeyGenerator.regionCityDataKey());
		setSerializer(new ProtostuffEntitySerializer<Integer, RegionCity>());
	}

	@Override
	protected List<RegionCity> loadFromDB() {
		return regionCityMapper.selectAll();
	}
	
	@Override
	@Autowired
	public void setRedis(Redis redis) {
		super.setRedis(redis);
	}
}
