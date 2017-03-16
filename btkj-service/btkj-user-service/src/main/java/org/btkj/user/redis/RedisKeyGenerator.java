package org.btkj.user.redis;

import java.text.MessageFormat;

public class RedisKeyGenerator {
	
	public static final String USER_LOCK				= "string:user:{0}:lock";			// 用户锁

	private static final String APP_DATA				= "hash:db:app";					// app 数据
	private static final String TENANT_DATA				= "hash:db:tenant";					// 租户数据
	private static final String USER_DATA				= "hash:db:user";					// 用户数据
	private static final String MOBILE_USER				= "hash:app:{0}:mobile:user";		// 每个 app 中用户手机和用户 uid 的映射关系 hash
	private static final String TOKEN_USER				= "hash:app:{0}:token:user";		// 每个 app 中用户  token 和用户 uid 的映射关系 hash
	private static final String USER_TOKEN				= "hash:app:{0}:user:token";		// 每个 app 中用户 uid 和 token 的映射关系
	
	private static final String USER_RELATION			= "hash:user:relation:{0}";			// 用户关系实体数据，有时间限制，其中 {0} 是 tid_uid 的格式
	private static final String TENANT_JOIN_APPLY		= "hash:tja:{0}";					// 租户加入申请数据，有时间限制
	
	// ************************************** string **************************************
	
	public static final String userLockKey(int uid) {
		return MessageFormat.format(USER_LOCK, String.valueOf(uid));
	}
	
	// ************************************** hash **************************************
	
	public final static String appDataKey() {
		return APP_DATA;
	}
	
	public final static String tenantDataKey() {
		return TENANT_DATA;
	}
	
	public static final String userDataKey() { 
		return USER_DATA;
	}
	
	public static final String mobileUserKey(int appId) { 
		return MessageFormat.format(MOBILE_USER, String.valueOf(appId));
	}
	
	public static final String tokenUserKey(int appId) { 
		return MessageFormat.format(TOKEN_USER, String.valueOf(appId));
	}
	
	public static final String userTokenKey(int appId) {
		return MessageFormat.format(USER_TOKEN, String.valueOf(appId));
	}
	
	public static final String userRelationKey(String key) { 
		return MessageFormat.format(USER_RELATION, key);
	}
	
	public static final String tenantJoinApplyKey(String key) { 
		return MessageFormat.format(TENANT_JOIN_APPLY, key);
	}
}
