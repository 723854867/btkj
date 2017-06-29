package org.btkj.pojo;

import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface BtkjConsts {
	
	final String ZK_CONFIG_PATH					= "/config/btkj";
	
	/**
	 * 租户顶级用户的 level
	 */
	final int EMPLOYEE_ROOT_LEVEL				= 1;
	
	final String CACHE_CONTROLLER_KEY			= "hash:memory:cache_controller";
	
	final int GLOBAL_TENANT_ID					= 0;
	final int MAX_COMMISION_RATE				= 600;
	final int MIN_COMMISION_RATE				= 0;
	final int MAX_COMMISION_RETAIN_RATE			= 200;
	final int MIN_COMMISION_RETAIN_RATE			= -100;
	
	interface RESULT {
		Result QUIZ_NOT_EXIST					= Result.result(BtkjCode.QUIZ_NOT_EXIST);
		Result ARTICLE_NOT_EXIST				= Result.result(BtkjCode.ARTICLE_NOT_EXIST);
		Result EMPLOYEE_NOT_EXIST				= Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Result USER_DATA_INCOMPLETE				= Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		Result USER_TENANT_NUM_MAXIMUM			= Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
		Result VEHICLE_BRAND_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_BRAND_NOT_EXSIT);
		Result VEHICLE_DEPT_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_DEPT_NOT_EXIST);
		Result VEHICLE_MODEL_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_MODEL_NOT_EXIST);
		Result VEHICLE_TYPE_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_TYPE_NOT_EXIST);
		
		Result EMPTY_PAGING						= Result.result(Pager.EMPLTY);
	}
	
	interface FIELD {
		String UID								= "uid";
	}
}
