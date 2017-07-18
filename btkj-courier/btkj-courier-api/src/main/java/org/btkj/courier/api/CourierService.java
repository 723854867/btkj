package org.btkj.courier.api;

import org.btkj.courier.model.QuotaNoticeSubmit;
import org.btkj.pojo.bo.CaptchaReceiver;
import org.btkj.pojo.bo.CaptchaVerifier;
import org.btkj.pojo.po.VehicleOrder;
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
	 * @param receiver 
	 * @return
	 */
	Result<String> captchaObtain(CaptchaReceiver receiver);
	
	/**
	 * 验证验证码
	 * 
	 * @param verifier
	 * @return
	 */
	Result<String> captchaVerify(CaptchaVerifier verifier);
	
	/**
	 * 报价通知：发送给客户的
	 * 
	 * @param order
	 * @param submit
	 * @return
	 */
	void quotaNotice(VehicleOrder order, QuotaNoticeSubmit submit);
}
