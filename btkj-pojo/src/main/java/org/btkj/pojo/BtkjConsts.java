package org.btkj.pojo;

import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

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
	final String HASH_CACHE_CONTROLLER					= "hash:memory:cache_controller";
	
	interface RESULT {
		Result QUIZ_NOT_EXIST					= Result.result(BtkjCode.QUIZ_NOT_EXIST);
		Result ARTICLE_NOT_EXIST				= Result.result(BtkjCode.ARTICLE_NOT_EXIST);
		Result EMPLOYEE_NOT_EXIST				= Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Result USER_DATA_INCOMPLETE				= Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		Result USER_TENANT_NUM_MAXIMUM			= Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
		
		Result EMPTY_PAGING						= Result.result(Pager.EMPLTY);
	}
	
	interface FIELD {
		String UID								= "uid";
	}
}
