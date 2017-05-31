package org.btkj.courier;

import org.rapid.util.common.Env;

public class Config {

	private static Env env;
	private static int captchaLen;				// 验证码位数
	private static int captchaMaximum;			// 验证码获取最大次数
	private static int captchaLifeTime;			// 验证码有效时间，该时间内不能再次获取
	private static int captchaCountLifeTime;	// 验证码次数有效时间，超过该时间不获取验证码，则次数清0
	private static int captchaTokenLifeTime;	// 验证码校验成功之后会生成一个 token，再该时间内操作只要带上该 token 都不需要再获取验证码
	
	public static Env getEnv() {
		return env;
	}
	
	public static void setEnv(String env) {
		Config.env = Env.match(env);
	}
	
	public static int getCaptchaLen() {
		return captchaLen;
	}
	
	public static void setCaptchaLen(int captchaLen) {
		Config.captchaLen = captchaLen;
	}
	
	public static int getCaptchaMaximum() {
		return captchaMaximum;
	}
	
	public static void setCaptchaMaximum(int captchaMaximum) {
		Config.captchaMaximum = captchaMaximum;
	}
	
	public static int getCaptchaLifeTime() {
		return captchaLifeTime;
	}
	
	public static void setCaptchaLifeTime(int captchaLifeTime) {
		Config.captchaLifeTime = captchaLifeTime;
	}
	
	public static int getCaptchaCountLifeTime() {
		return captchaCountLifeTime;
	}
	
	public static void setCaptchaCountLifeTime(int captchaCountLifeTime) {
		Config.captchaCountLifeTime = captchaCountLifeTime;
	}
	
	public static int getCaptchaTokenLifeTime() {
		return captchaTokenLifeTime;
	}
	
	public static void setCaptchaTokenLifeTime(int captchaTokenLifeTime) {
		Config.captchaTokenLifeTime = captchaTokenLifeTime;
	}
}
