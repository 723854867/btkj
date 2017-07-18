package org.btkj.courier.service;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.model.QuotaNoticeSubmit;
import org.btkj.courier.redis.CourierRedisService;
import org.btkj.pojo.bo.CaptchaReceiver;
import org.btkj.pojo.bo.CaptchaVerifier;
import org.btkj.pojo.po.VehicleOrder;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("courierService")
public class CourierServiceImpl implements CourierService {
	
	@Resource
	private CourierRedisService courierRedisService;

	@Override
	public Result<String> captchaObtain(CaptchaReceiver receiver) {
		return courierRedisService.captchaObtain(receiver);
	}
	
	@Override
	public Result<String> captchaVerify(CaptchaVerifier verifier) {
		return courierRedisService.captchaVerifier(verifier);
	}
	
	@Override
	public void quotaNotice(VehicleOrder order, QuotaNoticeSubmit submit) {
		courierRedisService.sendQuotaNotice(order, submit);
	}
	
	public void setCourierRedisService(CourierRedisService courierRedisService) {
		this.courierRedisService = courierRedisService;
	}
}
