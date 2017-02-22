package org.btkj.courier.api;

import org.btkj.courier.pojo.model.CaptchaReceiver;
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
	Result<String> captchaObtan(CaptchaReceiver receiver);
}
