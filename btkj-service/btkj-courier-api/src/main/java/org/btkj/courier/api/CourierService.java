package org.btkj.courier.api;

import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
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
}
