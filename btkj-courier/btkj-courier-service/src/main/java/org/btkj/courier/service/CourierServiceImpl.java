package org.btkj.courier.service;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.redis.CourierRedisService;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.param.QuoteNoticeParam;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("courierService")
public class CourierServiceImpl implements CourierService {
	
	@Resource
	private CourierRedisService courierRedisService;

	@Override
	public Result<String> captchaObtain(int appId, String mobile) {
		return courierRedisService.captchaObtain(appId, mobile);
	}
	
	@Override
	public Result<Void> captchaVerify(int appId, String mobile, String captcha) {
		return courierRedisService.captchaVerifier(appId, mobile, captcha);
	}
	
	@Override
	public void quotaNotice(VehicleOrder order, QuoteNoticeParam submit) {
		courierRedisService.sendQuotaNotice(order, submit);
	}
	
	public void setCourierRedisService(CourierRedisService courierRedisService) {
		this.courierRedisService = courierRedisService;
	}
}
