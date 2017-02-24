package org.btkj.config.deploy;

import org.btkj.config.redis.RedisService;
import org.rapid.util.common.Application;

public class Btkj extends Application {
	
	private RedisService redisService;

	@Override
	protected void start() {
		redisService.preLoading();
	}

	@Override
	protected void stop() {
	}
	
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
}
