package org.btkj.courier.redis;

import org.btkj.courier.Config;
import org.btkj.courier.pojo.model.CaptchaReceiver;
import org.rapid.redis.Redis;
import org.rapid.util.common.Env;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.key.KeyUtil;
import org.rapid.util.common.message.Result;

public class RedisService extends RedisKeyGenerator {

	private Redis redis;
	
	public Result<String> catpchaObtain(CaptchaReceiver receiver) {
		String captchaKey = captchaKey(receiver);
		String captchaCountKey = captchaCountKey(receiver);
		
		String captcha = KeyUtil.randomCaptcha(Config.getCaptchaLen());
		int flag = (int) redis.captchaObtain(captchaKey, captchaCountKey, captcha, 
				Config.getCaptchaLifeTime(), Config.getCaptchaMaximum(), Config.getCaptchaCountLifeTime());
		if (flag != 0) 
			return Result.result(flag);
			
		Env env = Config.getEnv();
		switch (env) {
		case LOCAL:
		case TEST:
			return Result.result(flag, captcha);
		case ONLINE:
			// send message
			return Result.result(flag);
		default:
			return Result.result(Code.SYSTEM_ERROR.id());
		}
	}
	
	public void setRedis(Redis redis) {
		this.redis = redis;
	}
}
