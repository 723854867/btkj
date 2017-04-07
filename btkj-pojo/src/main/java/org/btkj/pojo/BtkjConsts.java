package org.btkj.pojo;

public interface BtkjConsts {
	
	final String ZK_CONFIG_PATH					= "/config/btkj";

	/**
	 * 保途 app 的 app_id 固定为 0
	 */
	final int APP_ID_BAOTU						= 100;
	final int TENANT_ID_BAOTU					= 1000;
	
	/**
	 * tenant 的值为 null 的 credential
	 * 
	 */
	final String NULL_TENANT_CREDENTIAL			= "0000";
	
	/**
	 * 租户顶级用户的 level
	 */
	final int EMPLOYEE_ROOT_LEVEL				= 1;
}
