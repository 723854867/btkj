package org.btkj.courier.service;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.pojo.model.CaptchaReceiver;
import org.btkj.courier.redis.RedisService;
import org.rapid.util.common.message.Result;

public class CourierServiceImpl implements CourierService {
	
	private RedisService redisService;

	@Override
	public Result<String> captchaObtan(CaptchaReceiver receiver) {
		return redisService.catpchaObtain(receiver);
	}
	
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
}
