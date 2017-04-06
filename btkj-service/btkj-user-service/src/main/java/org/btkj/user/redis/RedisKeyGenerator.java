package org.btkj.user.redis;

import java.text.MessageFormat;

import org.btkj.pojo.model.ClientType;

public class RedisKeyGenerator {
	
	public static final String USER_LOCK				= "string:user:{0}:lock";			// 用户锁
	private static final String USER_CACHE_CONTROLLER	= "hash:user:{0}:cache:controller";	// 用户缓存控制器
	
	// tenant
	private static final String TENANT_DATA				= "hash:db:tenant";					// 租户数据
	private static final String BTKJ_TENANT				= "set:btkj:tenant";				// 保途的所有租户
	private static final String ISOLATE_APP_TENANT		= "hash:isolate:app:tenant";		// 独立 app 的 app_id 和 tid 的映射关系
	private static final String BTKJ_USER_MAIN_TENANT	= "hash:btkj:user:main:tenant";		// 保途 app 中每个用户的默认显示的代理商

	private static final String APP_DATA				= "hash:db:app";					// app 数据
	private static final String USER_DATA				= "hash:db:user";					// 用户数据
	private static final String BANNER_DATA				= "hash:db:banner";					// banner 条数据
	private static final String MOBILE_USER				= "hash:app:{0}:mobile:user";		// 每个 app 中用户手机和用户 uid 的映射关系 hash
	private static final String TOKEN_USER				= "hash:app:{0}:token:user:{1}";	// 每个 app 中用户  token 和用户 uid 的映射关系 hash, 1 - clientType
	private static final String USER_TOKEN				= "hash:app:{0}:user:token:{1}";	// 每个 app 中用户 uid 和 token 的映射关系, 1 - clientType
	
	// apply
	private static final String APPLY_DATA				= "hash:tenant:{0}:apply";			// 每个 租户的所有申请信息
	private static final String BTKJ_APPLY				= "set:btkj:apply:{0}";				// 保途 app 中用户 的所有申请数据
	private static final String TENANT_APPLY_LIST		= "set:tenant:{0}:apply:list";		// 代理公司的申请列表
	
	// employee
	private static final String EMPLOYEE_DATA			= "hash:db:employee";				// 雇员列表	
	private static final String USER_EMPLOYEE			= "hash:user:{0}:employee";			// 用户的雇员映射： 一般 field 为 tid，value 为 employee_id
	
	// ************************************** string **************************************
	
	public static final String userLockKey(int uid) {
		return MessageFormat.format(USER_LOCK, String.valueOf(uid));
	}
	
	public static final String userCacheControllerKey(int uid) {
		return MessageFormat.format(USER_CACHE_CONTROLLER, String.valueOf(uid));
	}
	
	// ************************************** hash **************************************
	
	public static final String btkjUserMainTenantKey() {
		return BTKJ_USER_MAIN_TENANT;
	}
	
	public final static String tenantDataKey() {
		return TENANT_DATA;
	}
	
	public static final String btkjTenantKey() {
		return BTKJ_TENANT;
	}
	
	public static final String isolateAppTenantKey() { 
		return ISOLATE_APP_TENANT;
	}
	
	public final static String appDataKey() {
		return APP_DATA;
	}
	
	public static final String userDataKey() { 
		return USER_DATA;
	}
	
	public static final String bannerDataKey() { 
		return BANNER_DATA;
	}
	
	public static final String mobileUserKey(int appId) { 
		return MessageFormat.format(MOBILE_USER, String.valueOf(appId));
	}
	
	public static final String tokenUserKey(int appId, ClientType ct) { 
		return MessageFormat.format(TOKEN_USER, String.valueOf(appId), String.valueOf(ct.type()));
	}
	
	public static final String userTokenKey(int appId, ClientType ct) {
		return MessageFormat.format(USER_TOKEN, String.valueOf(appId), String.valueOf(ct.type()));
	}
	
	public static final String applyDataKey(int tid) { 
		return MessageFormat.format(APPLY_DATA, String.valueOf(tid));
	}
	
	public static final String btkjApplyKey(Object uid) { 
		return MessageFormat.format(BTKJ_APPLY, uid.toString());
	}
	
	public static final String tenantApplyListKey(int tid) {
		return MessageFormat.format(TENANT_APPLY_LIST, String.valueOf(tid));
	}
	
	public static final String employeeDataKey() { 
		return EMPLOYEE_DATA;
	}
	
	public static final String userEmployeeKey(int uid) {
		return MessageFormat.format(USER_EMPLOYEE, String.valueOf(uid));
	}
}
