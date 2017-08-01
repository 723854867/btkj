package org.btkj.pojo;

import java.text.MessageFormat;

import org.btkj.pojo.bo.Pager;
import org.rapid.util.common.message.Result;

public interface BtkjConsts {
	
	final String ZK_CONFIG_PATH					= "/config/btkj";
	
	/**
	 * 租户顶级用户的 level
	 */
	final int EMPLOYEE_ROOT_LEVEL				= 1;
	
	final int GLOBAL_TENANT_ID					= 0;
	final int MAX_COMMISION_RATE				= 600;
	final int MIN_COMMISION_RATE				= 0;
	final int MAX_COMMISION_RETAIN_RATE			= 200;
	final int MIN_COMMISION_RETAIN_RATE			= -100;
	final int MIN_RENEWAL_PERIOD				= 30;
	final int MAX_RENEWAL_PERIOD				= 90;
	
	interface RESULT {
		Result APP_NOT_EXIST					= Result.result(BtkjCode.APP_NOT_EXIST);
		Result QUIZ_NOT_EXIST					= Result.result(BtkjCode.QUIZ_NOT_EXIST);
		Result LANE_NOT_EXIST					= Result.result(BtkjCode.LANE_NOT_EXIST);
		Result AREA_NOT_EXIST					= Result.result(BtkjCode.AREA_NOT_EXIST);
		Result ORDER_NOT_EXIST					= Result.result(BtkjCode.ORDER_NOT_EXIST);
		Result REPLY_NOT_EXIST					= Result.result(BtkjCode.REPLY_NOT_EXIST);
		Result ROUTE_NOT_EXIST					= Result.result(BtkjCode.ROUTE_NOT_EXIST);
		Result POLICY_NOT_EXIST					= Result.result(BtkjCode.POLICY_NOT_EXIST);
		Result REGION_NOT_EXIST					= Result.result(BtkjCode.REGION_NOT_EXIST);
		Result BANNER_NOT_EXIST					= Result.result(BtkjCode.BANNER_NOT_EXIST);
		Result TENANT_NOT_EXIST					= Result.result(BtkjCode.TENANT_NOT_EXIST);
		Result ORDER_STATE_ERROR				= Result.result(BtkjCode.ORDER_STATE_ERROR);
		Result ARTICLE_NOT_EXIST				= Result.result(BtkjCode.ARTICLE_NOT_EXIST);
		Result INSURER_NOT_EXIST				= Result.result(BtkjCode.INSURER_NOT_EXIST);
		Result LANE_CONFIG_ERROR				= Result.result(BtkjCode.LANE_CONFIG_ERROR);
		Result COMMENT_NOT_EXIST				= Result.result(BtkjCode.COMMENT_NOT_EXIST);
		Result JIAN_JIE_ID_NEEDED				= Result.result(BtkjCode.JIAN_JIE_ID_NEEDED);
		Result EMPLOYEE_NOT_EXIST				= Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Result CUSTOMER_NOT_EXIST				= Result.result(BtkjCode.CUSTOMER_NOT_EXIST);
		Result BONUS_SCALE_REWARDED				= Result.result(BtkjCode.BONUS_SCALE_REWARDED);
		Result USER_DATA_INCOMPLETE				= Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		Result COEFFICIENT_NOT_EXIST			= Result.result(BtkjCode.COEFFICIENT_NOT_EXIST);
		Result BONUS_SCALE_NOT_EXIST			= Result.result(BtkjCode.BONUS_SCALE_NOT_EXIST);
		Result USER_TENANT_NUM_MAXIMUM			= Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
		Result VEHICLE_BRAND_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_BRAND_NOT_EXSIT);
		Result VEHICLE_DEPT_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_DEPT_NOT_EXIST);
		Result VEHICLE_MODEL_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_MODEL_NOT_EXIST);
		Result VEHICLE_TYPE_NOT_EXIST			= Result.result(BtkjCode.VEHICLE_TYPE_NOT_EXIST);
		Result COEFFICIENT_NUM_MAXMIUM			= Result.result(BtkjCode.COEFFICIENT_NUM_MAXMIUM);
		Result BONUS_SCALE_SETTINGS_ERROR		= Result.result(BtkjCode.BONUS_SCALE_SETTINGS_ERROR);
		Result NON_AUTO_CATEGORY_NOT_EXIST		= Result.result(BtkjCode.NON_AUTO_CATEGORY_NOT_EXIST);
		Result CUSTOMER_IDENTITY_DUPLICATE		= Result.result(BtkjCode.CUSTOMER_IDENTITY_DUPLICATE);
		Result BONUS_SCALE_CONFIG_NOT_EXIST		= Result.result(BtkjCode.BONUS_SCALE_CONFIG_NOT_EXIST);
		Result BONUS_MANAGE_CONFIG_NOT_EXIST	= Result.result(BtkjCode.BONUS_MANAGE_CONFIG_NOT_EXIST);
		Result BI_HU_TENANT_CONFIG_NOT_EXIST	= Result.result(BtkjCode.BI_HU_TENANT_CONFIG_NOT_EXIST);
		
		Result EMPTY_PAGING						= Result.result(Pager.EMPLTY);
	}
	
	interface FIELD {
		String UID								= "uid";
	}
	
	interface LIMITS {
		final int ARTICLE_TITLE_MIN						= 6;				// 咨询标题最小值
		final int ARTICLE_TITLE_MAX						= 50;				// 咨询标题最大值
		final int URL_MIN								= 6;				// url 最小值
		final int URL_MAX								= 200;				// url 最大值
		final int NAME_MIN								= 2;
		final int NAME_MAX								= 50;
		final int NONAUTO_MAX							= 8;				// 非车险类型最大数
		final int NONAUTO_FILTER_MAX					= 5;				// 非车险分类最大筛选类别数
		final int NONAUTO_FILTER_OPTION_MIN				= 2;				// 非车险分类最小筛选项数
		final int NONAUTO_FILTER_OPTION_MAX				= 6;				// 非车险分类最大筛选项数
		final int NONAUTO_SORT_MAX						= 6;				// 非车险分类最大排序类型数
		final int NONAUTO_TAG_MAX						= 8;				// 非车险分类最大标签数
		final int NONAUTO_LIABILITY_MIN					= 1;				// 非车险产品保险责任最小数
		final int NONAUTO_LIABILITY_MAX					= 6;				// 非车险产品保险责任最大数
		final int PHONE_MIN								= 6;				// 电话字段最少字符
		final int PHONE_MAX								= 15;				// 电话字段最大字符
	}
	
	interface LOCKS {
		static String tenantResourceLock(int tid) {
			return MessageFormat.format("lock:tenant:resource:{0}", String.valueOf(tid));
		}
	}
}
