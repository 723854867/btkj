package org.btkj.config.redis.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.persistence.mapper.RegionDistrictMapper;
import org.btkj.config.pojo.entity.RegionDistrict;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.redis.Redis;
import org.rapid.redis.service.RedisService;
import org.rapid.util.common.db.ProtostuffEntitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionDistrictRedisService extends RedisService<Integer, RegionDistrict> {
	
	@Resource
	private RegionDistrictMapper regionDistrictMapper;
	
	protected RegionDistrictRedisService() {
		super(RegionDistrict.class, RedisKeyGenerator.regionDistrictDataKey());
		setSerializer(new ProtostuffEntitySerializer<Integer, RegionDistrict>());
	}

	@Override
	protected List<RegionDistrict> loadFromDB() {
		return regionDistrictMapper.selectAll();
	}
	
	@Override
	@Autowired
	public void setRedis(Redis redis) {
		super.setRedis(redis);
	}
}
