package org.btkj.courier.redis;

import java.text.MessageFormat;

import org.btkj.courier.pojo.model.CaptchaReceiver;

public class RedisKeyGenerator {

	private static final String CAPTCHA					= "string:tmp:captcha:{0}:{1}:record";			// 保存验证码：0-类型，1-设备号
	private static final String CAPTCHA_COUNT			= "string:tmp:captcha:{0}:{1}:count";			// 验证码获取次数：0-类型，1-设备号
	
	public static final String captchaKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA, receiver.getType().name(), receiver.getIdentity());
	}
	
	public static final String captchaCountKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA_COUNT, receiver.getType().name(), receiver.getIdentity());
	}
}
