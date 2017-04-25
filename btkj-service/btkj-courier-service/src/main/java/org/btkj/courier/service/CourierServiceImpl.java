package org.btkj.courier.service;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.redis.RedisService;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("courierService")
public class CourierServiceImpl implements CourierService {
	
	@Resource
	private RedisService redisService;

	@Override
	public Result<String> captchaObtain(CaptchaReceiver receiver) {
		return redisService.captchaObtain(receiver);
	}
	
	@Override
	public Result<String> captchaVerify(CaptchaVerifier verifier) {
		return redisService.captchaVerifier(verifier);
	}
	
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
}
