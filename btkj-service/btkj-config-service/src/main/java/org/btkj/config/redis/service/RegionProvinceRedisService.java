package org.btkj.config.redis.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.persistence.mapper.RegionProvinceMapper;
import org.btkj.config.pojo.entity.RegionProvince;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.redis.Redis;
import org.rapid.redis.service.RedisService;
import org.rapid.util.common.db.ProtostuffEntitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionProvinceRedisService extends RedisService<Integer, RegionProvince> {
	
	@Resource
	private RegionProvinceMapper regionProvinceMapper;
	
	protected RegionProvinceRedisService() {
		super(RegionProvince.class, RedisKeyGenerator.regionProvinceDataKey());
		setSerializer(new ProtostuffEntitySerializer<Integer, RegionProvince>());
	}

	@Override
	protected List<RegionProvince> loadFromDB() {
		return regionProvinceMapper.selectAll();
	}
	
	@Override
	@Autowired
	public void setRedis(Redis redis) {
		super.setRedis(redis);
	}
}
