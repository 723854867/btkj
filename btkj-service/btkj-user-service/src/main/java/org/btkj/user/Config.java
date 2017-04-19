package org.btkj.user;

public class Config {
	
	public static final String CACHE_CONTROLLER						= "set:cache:controller";

	private static int userLockExpire;			// 用户锁超时时间
	
	public static int getUserLockExpire() {
		return userLockExpire;
	}
	
	public static void setUserLockExpire(int userLockExpire) {
		Config.userLockExpire = userLockExpire;
	}
}
