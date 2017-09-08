package org.btkj.web.util;

import java.util.TimeZone;

import javax.validation.Validation;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.info.QuizSearcher;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.btkj.pojo.model.Version;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.conveter.Str2IntConstConverter;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.common.consts.conveter.Str2StrConstConverter;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.PhoneUtil;
import org.rapid.util.validator.Validator;

/**
 * btkj 的所有请求参数
 * 
 * @author ahab
 *
 */
public interface Params {
	
	final javax.validation.Validator JSR_VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
	
	/**
	 * 头部信息是可选的，因此这里不需要 id, global session id
	 */
	final Str2StrConstConverter SESSION_ID				= new Str2StrConstConverter("sessionId");
	
	/**
	 * 雇员 ID
	 */
	final Str2IntConstConverter EMPLOYEE_ID				= new Str2IntConstConverter(1000, "employeeId");
	
	final Str2StrConstConverter CONTENT					= new Str2StrConstConverter(1001, "content");
	
	/**
	 * 登陆或者注册时需要传递的参数，用来区分不同的 app、tenant
	 * 
	 */
	final Str2IntConstConverter APP_ID					= new Str2IntConstConverter(1002, "appId", 0);
	final Str2IntConstConverter TYPE					= new Str2IntConstConverter(1003, "type");
	final Str2StrConstConverter TOKEN					= new Str2StrConstConverter(1004, "token");
	final Str2StrConstConverter ACTION					= new Str2StrConstConverter(1005, "action");
	
	/**
	 * 泛指名字
	 */
	final Str2StrConstConverter NAME					= new Str2StrConstConverter(1006, "name") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isLetterDigitOrChinese(value))
				throw ConstConvertFailureException.errorConstException(this);
			return value;
		}
	};
	
	final Str2IntConstConverter CUSTOMER_ID				= new Str2IntConstConverter(1008, "customerId");

	
	/**
	 * 需要严格检查手机的格式，返回的是 "+" + 国家编号 + 手机号码，比如 +8613888888888
	 */
	final Str2StrConstConverter MOBILE					= new Str2StrConstConverter(1009, "mobile") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!PhoneUtil.isMobile(value))
				throw ConstConvertFailureException.errorConstException(this);
			return Consts.SYMBOL_PLUS + PhoneUtil.getCountryCode(value) + PhoneUtil.getNationalNumber(value);
		};
	};
	
	final Str2StrConstConverter AVATAR 					= new Str2StrConstConverter(1011, "avatar");
	
	final Str2IntConstConverter TID						= new Str2IntConstConverter(1012, "tid");
	
	final Str2ObjConstConverter<Version> VERSION		= new Str2ObjConstConverter<Version>(1013, "version", Version.V_1_0) {
		@Override
		public Version convert(String k) throws ConstConvertFailureException {
			return Version.match(k);
		}
	};
	
	final Str2StrConstConverter ADDRESS 					= new Str2StrConstConverter(1014, "address");
	
	final Str2StrConstConverter CAPTCHA					= new Str2StrConstConverter(1015, "captcha");
	
	/**
	 * 身份证
	 */
	final Str2StrConstConverter IDENTITY				= new Str2StrConstConverter(1016, "identity") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isIdentity(value))
				throw ConstConvertFailureException.errorConstException(this);
			return value;
		};
	};
	
	final Str2IntConstConverter REGION					= new Str2IntConstConverter(1017, "region");
	
	final Str2ObjConstConverter<Client> CLIENT			= new Str2ObjConstConverter<Client>(1021, "client", Client.APP) {
		@Override
		public Client convert(String k) throws ConstConvertFailureException {
			Client client;
			try {
				client = Client.match(Integer.valueOf(k));
				if (null == client)
					throw ConstConvertFailureException.errorConstException(this);
				return client;
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this, e);
			}
		}
	};
	final Str2StrConstConverter PWD						= new Str2StrConstConverter(1022, "pwd") {
		public String convert(String value) throws ConstConvertFailureException {
			return DigestUtils.md5Hex(value);
		};
	};
	final Str2IntConstConverter ID						= new Str2IntConstConverter(1024, "id");
	
	final Str2ObjConstConverter<DeliveryType> DELIVERY_TYPE	= new Str2ObjConstConverter<DeliveryType>(1028, "deliveryType") {
		@Override
		public DeliveryType convert(String k) throws ConstConvertFailureException {
			try {
				int value = Integer.valueOf(k);
				DeliveryType type = DeliveryType.match(value);
				if (null == type)
					throw ConstConvertFailureException.errorConstException(this);
				return type;
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
	
	final Str2StrConstConverter IDENTITY_FACE						= new Str2StrConstConverter(1029, "identityFace");
	final Str2StrConstConverter IDENTITY_BACK						= new Str2StrConstConverter(1030, "identityBack");
	
	final Str2IntConstConverter MOD						= new Str2IntConstConverter(1035, "mod");
	final Str2IntConstConverter BEGIN_TIME				= new Str2IntConstConverter(1036, "beginTime") {
		public Integer convert(String value) throws ConstConvertFailureException {
			return (int) (DateUtil.getTime(value, DateUtil.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault()) / 1000);
		};
	};
	
	final Str2IntConstConverter END_TIME				= new Str2IntConstConverter(1037, "endTime") {
		public Integer convert(String value) throws ConstConvertFailureException {
			return (int) (DateUtil.getTime(value, DateUtil.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault()) / 1000);
		};
	};
	
	final Str2StrConstConverter ORDER_ID						= new Str2StrConstConverter(1038, "orderId");
	
	final Str2IntConstConverter PAGE					= new Str2IntConstConverter(1100, "page", 1);
	final Str2IntConstConverter PAGE_SIZE				= new Str2IntConstConverter(1101, "pageSize", 10);

	/**
	 * 车牌号
	 */
	final Str2StrConstConverter LICENSE					= new Str2StrConstConverter(1102, "license") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isVehicleLisense(value))
				throw ConstConvertFailureException.errorConstException(this);
			return value;
		};
	};
	
	final Str2StrConstConverter VIN						= new Str2StrConstConverter(1103, "vin");
	final Str2StrConstConverter ENGINE					= new Str2StrConstConverter(1104, "engine");
	
	final Str2ObjConstConverter<VehiclePolicyTips> VEHICLE_POLICY_TIPS	= new Str2ObjConstConverter<VehiclePolicyTips>(1105, "vehiclePolicyTips") {
		@Override
		public VehiclePolicyTips convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, VehiclePolicyTips.class);
		}
	};
	
	final Str2ObjConstConverter<CustomerSearcher> CUSTOMER_SEARCHER	= new Str2ObjConstConverter<CustomerSearcher>(1110, "customerSearcher") {
		@Override
		public CustomerSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, CustomerSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<QuizSearcher> QUIZ_SEARCHER				= new Str2ObjConstConverter<QuizSearcher>(1211, "quizSearcher") {
		@Override
		public QuizSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, QuizSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<CrudType> CRUD_TYPE = new Str2ObjConstConverter<CrudType>(1212, "crudType") {
		@Override
		public CrudType convert(String k) throws ConstConvertFailureException {
			int value;
			try {
				value = Integer.valueOf(k);
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
			CrudType type = org.rapid.util.common.enums.CrudType.match(value);
			if (null == type)
				throw ConstConvertFailureException.errorConstException(this);
			return type;
		}
	};	
	
	final Str2StrConstConverter PAYLOAD					= new Str2StrConstConverter(1218, "payload");
}
