package org.btkj.pojo;

public interface BtkjConsts {
	
	final String ZK_CONFIG_PATH					= "/config/btkj";
	
	/**
	 * 租户顶级用户的 level
	 */
	final int EMPLOYEE_ROOT_LEVEL				= 1;
	
	/**
	 * redis 缓存控制器的 key
	 * 
	 */
	final String CACHE_CONTROLLER						= "set:cache:controller";
	
	@SuppressWarnings("rawtypes")
	interface RESULT {
		
	}
}
