package org.btkj.courier.service;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.courier.pojo.submit.QuotaNoticeSubmit;
import org.btkj.courier.redis.CourierRedisService;
import org.btkj.pojo.po.VehicleOrder;
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
	public Result<String> captchaVerify(int appId, String mobile, String captcha) {
		return courierRedisService.captchaVerifier(appId, mobile, captcha);
	}
	
	@Override
	public void quotaNotice(VehicleOrder order, QuotaNoticeSubmit submit) {
		courierRedisService.sendQuotaNotice(order, submit);
	}
	
	public void setCourierRedisService(CourierRedisService courierRedisService) {
		this.courierRedisService = courierRedisService;
	}
}
