package org.btkj.courier.api;

import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.param.QuoteNoticeParam;
import org.rapid.util.common.message.Result;

/**
 * 信使服务类：包括短信发送、邮件发送、推送、im等
 * 
 * @author ahab
 */
public interface CourierService {

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	Result<String> captchaObtain(int appId, String mobile);
	
	/**
	 * 验证验证码
	 * 
	 * @return
	 */
	Result<Void> captchaVerify(int appId, String mobile, String captcha);
	
	/**
	 * 报价通知：发送给客户的
	 * 
	 * @param order
	 * @param submit
	 * @return
	 */
	void quotaNotice(VehicleOrder order, QuoteNoticeParam submit);
}
