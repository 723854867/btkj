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
		Result LANE_NOT_EXIST					= Result.result(BtkjCode.LANE_NOT_EXIST);
		Result CITY_NOT_EXIST					= Result.result(BtkjCode.CITY_NOT_EXIST);
		Result ORDER_NOT_EXIST					= Result.result(BtkjCode.ORDER_NOT_EXIST);
		Result REPLY_NOT_EXIST					= Result.result(BtkjCode.REPLY_NOT_EXIST);
		Result ROUTE_NOT_EXIST					= Result.result(BtkjCode.ROUTE_NOT_EXIST);
		Result BANNER_NOT_EXIST					= Result.result(BtkjCode.BANNER_NOT_EXIST);
		Result TENANT_NOT_EXIST					= Result.result(BtkjCode.TENANT_NOT_EXIST);
		Result ORDER_STATE_ERROR				= Result.result(BtkjCode.ORDER_STATE_ERROR);
		Result ARTICLE_NOT_EXIST				= Result.result(BtkjCode.ARTICLE_NOT_EXIST);
		Result INSURER_NOT_EXIST				= Result.result(BtkjCode.INSURER_NOT_EXIST);
		Result COMMENT_NOT_EXIST				= Result.result(BtkjCode.COMMENT_NOT_EXIST);
		Result JIAN_JIE_ID_NEEDED				= Result.result(BtkjCode.JIAN_JIE_ID_NEEDED);
		Result EMPLOYEE_NOT_EXIST				= Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Result CUSTOMER_NOT_EXIST				= Result.result(BtkjCode.CUSTOMER_NOT_EXIST);
		Result USER_DATA_INCOMPLETE				= Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		Result COEFFICIENT_NOT_EXIST			= Result.result(BtkjCode.COEFFICIENT_NOT_EXIST);
		Result USER_TENANT_NUM_MAXIMUM			= Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
		Result VEHICLE_BRAND_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_BRAND_NOT_EXSIT);
		Result VEHICLE_DEPT_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_DEPT_NOT_EXIST);
		Result VEHICLE_MODEL_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_MODEL_NOT_EXIST);
		Result VEHICLE_TYPE_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_TYPE_NOT_EXIST);
		Result COEFFICIENT_NUM_MAXMIUM			= Result.result(BtkjCode.COEFFICIENT_NUM_MAXMIUM);
		Result CUSTOMER_IDENTITY_DUPLICATE		= Result.result(BtkjCode.CUSTOMER_IDENTITY_DUPLICATE);
		Result BONUS_SCALE_CONFIG_NOT_EXIST		= Result.result(BtkjCode.BONUS_SCALE_CONFIG_NOT_EXIST);
		Result BONUS_MANAGE_CONFIG_NOT_EXIST	= Result.result(BtkjCode.BONUS_MANAGE_CONFIG_NOT_EXIST);
		
		Result EMPTY_PAGING						= Result.result(Pager.EMPLTY);
	}
	
	interface FIELD {
		String UID								= "uid";
	}
}
