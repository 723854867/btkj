package org.btkj.config.redis.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.persistence.mapper.InsuranceMapper;
import org.btkj.config.pojo.entity.Insurance;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.redis.Redis;
import org.rapid.redis.service.RedisService;
import org.rapid.util.common.db.ProtostuffEntitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceRedisService extends RedisService<Integer, Insurance> {
	
	@Resource
	private InsuranceMapper insuranceMapper;
	
	protected InsuranceRedisService() {
		super(Insurance.class, RedisKeyGenerator.insuranceDataKey());
		setSerializer(new ProtostuffEntitySerializer<Integer, Insurance>());
	}

	@Override
	protected List<Insurance> loadFromDB() {
		return insuranceMapper.selectAll();
	}
	
	@Override
	@Autowired
	public void setRedis(Redis redis) {
		super.setRedis(redis);
	}
}
