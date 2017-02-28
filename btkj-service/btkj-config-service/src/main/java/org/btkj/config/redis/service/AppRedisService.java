package org.btkj.config.redis.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.persistence.mapper.AppMapper;
import org.btkj.config.pojo.entity.App;
import org.btkj.config.redis.RedisKeyGenerator;
import org.rapid.redis.Redis;
import org.rapid.redis.service.RedisService;
import org.rapid.util.common.db.ProtostuffEntitySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRedisService extends RedisService<Integer, App> {
	
	@Resource
	private AppMapper appMapper;
	
	public AppRedisService() {
		super(App.class, RedisKeyGenerator.appDataKey());
		setSerializer(new ProtostuffEntitySerializer<Integer, App>());
	}

	@Override
	protected List<App> loadFromDB() {
		return appMapper.selectAll();
	}
	
	@Override
	@Autowired
	public void setRedis(Redis redis) {
		super.setRedis(redis);
	}
}
