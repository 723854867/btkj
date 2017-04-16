package org.btkj.user.redis;

import java.text.MessageFormat;

public class RedisKeyGenerator {
	
	private static final String TABLE_MODIFIER			= "hash:table:modifier";
	public static final String USER_LOCK				= "string:user:{0}:lock";			// 用户锁
	private static final String USER_CACHE_CONTROLLER	= "hash:user:{0}:cache:controller";	// 用户缓存控制器
	
	// tenant
	private static final String TENANT_DATA				= "hash:db:tenant";					// 租户数据

	private static final String BANNER_DATA				= "hash:db:banner";					// banner 条数据
	
	// employee
	
	// ************************************** string **************************************
	
	public static final String userLockKey(int uid) {
		return MessageFormat.format(USER_LOCK, String.valueOf(uid));
	}
	
	public static final String userCacheControllerKey(int uid) {
		return MessageFormat.format(USER_CACHE_CONTROLLER, String.valueOf(uid));
	}
	
	// ************************************** hash **************************************
	
	public static final String tableModifierKey() {
		return TABLE_MODIFIER;
	}
	
	public final static String tenantDataKey() {
		return TENANT_DATA;
	}
	
	public static final String bannerDataKey() { 
		return BANNER_DATA;
	}
}
