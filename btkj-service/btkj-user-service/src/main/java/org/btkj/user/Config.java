package org.btkj.user;

public class Config {

	private static int userLockExpire;			// 用户锁超时时间
	private static int captchaTokenExpire;		// 验证码验证通过之后后续操作在该时间内可以不用再次验证
	
	public static int getUserLockExpire() {
		return userLockExpire;
	}
	
	public static void setUserLockExpire(int userLockExpire) {
		Config.userLockExpire = userLockExpire;
	}
	
	public static int getCaptchaTokenExpire() {
		return captchaTokenExpire;
	}
	
	public static void setCaptchaTokenExpire(int captchaTokenExpire) {
		Config.captchaTokenExpire = captchaTokenExpire;
	}
}
