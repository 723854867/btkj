package org.btkj.courier.redis;

import java.text.MessageFormat;

import org.btkj.pojo.model.CaptchaReceiver;

public class RedisKeyGenerator {

	private static final String CAPTCHA				= "string:app:{0}:captcha:record:{1}:{2}";	// 保存验证码：0-app类型，1-设备类型，2-设备号
	private static final String CAPTCHA_COUNT		= "string:app:{0}:captcha:count:{1}:{2}";	// 验证码获取次数：0-app类型，1-设备类型，2-设备
	
	public static final String captchaKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA, String.valueOf(receiver.getAppId()), receiver.getType().name(), receiver.getIdentity());
	}
	
	public static final String captchaCountKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA_COUNT, String.valueOf(receiver.getAppId()), receiver.getType().name(), receiver.getIdentity());
	}
}
