package org.btkj.config.redis;

import org.rapid.redis.Redis;

public class RedisService extends RedisKeyGenerator {

	private Redis redis;
	
	public void setRedis(Redis redis) {
		this.redis = redis;
	}
}
