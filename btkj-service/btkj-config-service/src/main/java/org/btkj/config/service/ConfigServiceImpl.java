package org.btkj.config.service;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.RedisService;

public class ConfigServiceImpl implements ConfigService {

	private RedisService redisService;
	
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
}
