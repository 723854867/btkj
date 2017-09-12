package org.btkj.pojo;

import java.text.MessageFormat;

import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface BtkjConsts {
	
	final String ZK_CONFIG_PATH					= "/config/btkj";
	
	final int GLOBAL_TENANT_ID					= 0;
	
	interface RESULT {
		Result APP_SEALED						= Result.result(BtkjCode.APP_SEALED);
		Result USER_SEALED						= Result.result(BtkjCode.USER_SEALED);
		Result APPLY_EXIST						= Result.result(BtkjCode.APPLY_EXIST);
		Result ADMIN_SEALED						= Result.result(BtkjCode.ADMIN_SEALED);
		Result TENANT_SEALED					= Result.result(BtkjCode.TENANT_SEALED);
		Result APP_NOT_EXIST					= Result.result(BtkjCode.APP_NOT_EXIST);
		Result QUIZ_NOT_EXIST					= Result.result(BtkjCode.QUIZ_NOT_EXIST);
		Result AREA_NOT_EXIST					= Result.result(BtkjCode.AREA_NOT_EXIST);
		Result EMPLOYEE_SEALED					= Result.result(BtkjCode.EMPLOYEE_SEALED);
		Result ADMIN_NOT_EXIST					= Result.result(BtkjCode.ADMIN_NOT_EXIST);
		Result ORDER_NOT_EXIST					= Result.result(BtkjCode.ORDER_NOT_EXIST);
		Result REPLY_NOT_EXIST					= Result.result(BtkjCode.REPLY_NOT_EXIST);
		Result POLICY_NOT_EXIST					= Result.result(BtkjCode.POLICY_NOT_EXIST);
		Result REGION_NOT_EXIST					= Result.result(BtkjCode.REGION_NOT_EXIST);
		Result BANNER_NOT_EXIST					= Result.result(BtkjCode.BANNER_NOT_EXIST);
		Result TENANT_NOT_EXIST					= Result.result(BtkjCode.TENANT_NOT_EXIST);
		Result VEHICLE_INFO_NILL				= Result.result(BtkjCode.VEHICLE_INFO_NILL);
		Result ORDER_STATE_ERROR				= Result.result(BtkjCode.ORDER_STATE_ERROR);
		Result ARTICLE_NOT_EXIST				= Result.result(BtkjCode.ARTICLE_NOT_EXIST);
		Result INSURER_NOT_EXIST				= Result.result(BtkjCode.INSURER_NOT_EXIST);
		Result COMMENT_NOT_EXIST				= Result.result(BtkjCode.COMMENT_NOT_EXIST);
		Result MODULAR_NOT_EXIST				= Result.result(BtkjCode.MODULAR_NOT_EXIST);
		Result JIAN_JIE_ID_NEEDED				= Result.result(BtkjCode.JIAN_JIE_ID_NEEDED);
		Result EMPLOYEE_NOT_EXIST				= Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Result CUSTOMER_NOT_EXIST				= Result.result(BtkjCode.CUSTOMER_NOT_EXIST);
		Result ALREADY_IS_EMPLOYEE				= Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		Result BONUS_SCALE_REWARDED				= Result.result(BtkjCode.BONUS_SCALE_REWARDED);
		Result USER_DATA_INCOMPLETE				= Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		Result BONUS_SCALE_NOT_EXIST			= Result.result(BtkjCode.BONUS_SCALE_NOT_EXIST);
		Result LANE_BI_HU_NOT_OPENED			= Result.result(BtkjCode.LANE_BI_HU_NOT_OPENED);
		Result ERROR_INSURANCE_SCHEMA			= Result.result(BtkjCode.ERROR_INSURANCE_SCHEMA);
		Result USER_TENANT_NUM_MAXIMUM			= Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
		Result INSURER_UNSUPPORT_BI_HU			= Result.result(BtkjCode.INSURER_UNSUPPORT_BI_HU);
		Result LANE_LE_BAO_BA_NOT_OPENED		= Result.result(BtkjCode.LANE_LE_BAO_BA_NOT_OPENED);
		Result POUNDAGE_NODE_NOT_EXIST			= Result.result(BtkjCode.POUNDAGE_NODE_NOT_EXIST);
		Result INSURER_UNSUPPORT_LE_BAO_BA		= Result.result(BtkjCode.INSURER_UNSUPPORT_LE_BAO_BA);
		Result BONUS_SCALE_SETTINGS_ERROR		= Result.result(BtkjCode.BONUS_SCALE_SETTINGS_ERROR);
		Result NON_AUTO_CATEGORY_NOT_EXIST		= Result.result(BtkjCode.NON_AUTO_CATEGORY_NOT_EXIST);
		Result CUSTOMER_IDENTITY_DUPLICATE		= Result.result(BtkjCode.CUSTOMER_IDENTITY_DUPLICATE);
		Result COMPARISON_VALUE_ERROR			= Result.result(BtkjCode.COMPARISON_VALUE_ERROR);
		Result BONUS_SCALE_CONFIG_NOT_EXIST		= Result.result(BtkjCode.BONUS_SCALE_CONFIG_NOT_EXIST);
		Result BONUS_MANAGE_CONFIG_NOT_EXIST	= Result.result(BtkjCode.BONUS_MANAGE_CONFIG_NOT_EXIST);
		Result POUNDAGE_COEFFICIENT_NOT_CUSTOM	= Result.result(BtkjCode.POUNDAGE_COEFFICIENT_NOT_CUSTOM);
		Result POUNDAGE_COEFFICIENT_NOT_EXIST	= Result.result(BtkjCode.POUNDAGE_COEFFICIENT_NOT_EXIST);
		Result INSURER_MOD_NOT_SUBSET_OF_QUOTE	= Result.result(BtkjCode.INSURER_MOD_NOT_SUBSET_OF_QUOTE);
		Result ID_TYPE_UNSUITABLE_TO_UNIT_TYPE	= Result.result(BtkjCode.ID_TYPE_UNSUITABLE_TO_UNIT_TYPE);
		Result POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST	= Result.result(BtkjCode.POUNDAGE_COEFFICIENT_RANGE_NOT_EXIST);
		
		Result EMPTY_PAGING						= Result.result(Pager.EMPLTY);
	}
	
	interface FIELD {
		String ID								= "id";
		String UID								= "uid";
		String TID								= "tid";
		String APPID							= "appId";
		String APP_ID							= "app_id";
		String PARENT_ID						= "parent_id";
		String EMPLOYEEID						= "employeeId";
		String EMPLOYEE_ID						= "employee_id";
		String STATE							= "state";
		String CREATED							= "created";
		String POLICYID							= "policyId";
		String INSURERID						= "insurerId";
		String INSURER_ID						= "insurer_id";
		String LICENSE							= "license";
		String OWNER							= "owner";
		String SALESMAN							= "salesman";
		String TRANSFER							= "transfer";
		String NATURE							= "nature";
		String TYPE								= "type";
		String NAME								= "name";
		String BONUSTYPE						= "bonusType";
		String ISSUETIME						= "issueTime";
		String MOBILE							= "mobile";
		String INSURANCE_TYPE					= "insurance_type";
		String YEAR								= "year";
		String MONTH							= "month";
		String DAY								= "day";
		String WEEK								= "week";
		String SEASON							= "season";
	}
	
	interface LIMITS {
		final int ARTICLE_TITLE_MIN						= 6;				// 咨询标题最小值
		final int ARTICLE_TITLE_MAX						= 50;				// 咨询标题最大值
		final int URL_MIN								= 6;				// url 最小值
		final int URL_MAX								= 200;				// url 最大值
		final int NAME_MIN								= 1;
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
		final int MAX_COMMISION_RATE					= 600;
		final int MIN_COMMISION_RATE					= 0;
		final int MAX_COMMISION_RETAIN_RATE				= 200;
		final int MIN_COMMISION_RETAIN_RATE				= -100;
		final int MIN_COEFFICIENT_RANGE_RATIO			= -500;
		final int MAX_COEFFICIENT_RANGE_RATIO			= 500;
		final int MIN_RENEWAL_PERIOD					= 30;
		final int MAX_RENEWAL_PERIOD					= 90;
		final int MIN_PWD								= 6;
		final int MAX_PWD								= 30;
		final int MAX_POUNDAGE_RATE						= 800;
		final int MIN_POUNDAGE_RATE						= 0;
	}
	
	interface LOCKS {
		// 模块锁
		static String modularLock() {
			return "lock:modular";
		}
		static String tenantResourceLock(int tid) {
			return MessageFormat.format("lock:tenant:resource:{0}", String.valueOf(tid));
		}
	}
}
