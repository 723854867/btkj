package org.btkj.web.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.enums.AppState;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.EmployeeState;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.model.Version;
import org.btkj.pojo.submit.AppSearcher;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.btkj.pojo.submit.UserSearcher;
import org.rapid.util.common.Validator;
import org.rapid.util.common.consts.conveter.Str2BoolConstConverter;
import org.rapid.util.common.consts.conveter.Str2IntConstConverter;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.common.consts.conveter.Str2StrConstConverter;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;

import com.google.gson.reflect.TypeToken;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * btkj 的所有请求参数
 * 
 * @author ahab
 *
 */
public interface Params {
	
	final Type INT_SET_TYPE								 = new TypeToken<List<Integer>>(){}.getType();
	
	/**
	 * 头部信息是可选的，因此这里不需要 id, global session id
	 */
	final Str2StrConstConverter SESSION_ID				= new Str2StrConstConverter("sessionId");
	
	/**
	 * 雇员 ID
	 */
	final Str2IntConstConverter EMPLOYEE_ID				= new Str2IntConstConverter(1000, "employeeId");
	
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
	final Str2StrConstConverter NAME					= new Str2StrConstConverter(1006, "name");
	
	/**
	 * 租户名
	 */
	final Str2StrConstConverter TNAME					= new Str2StrConstConverter(1007, "tname");

	
	/**
	 * 需要严格检查手机的格式，返回的是 "+" + 国家编号 + 手机号码，比如 +8613888888888
	 */
	final Str2StrConstConverter MOBILE					= new Str2StrConstConverter(1009, "mobile") {
		public String convert(String value) throws ConstConvertFailureException {
			PhoneNumberUtil util = PhoneNumberUtil.getInstance();
			try {
				PhoneNumber number = util.parse(value, null);
				if (!util.isValidNumber(number))
					throw new ConstConvertFailureException(this);
				return "+" + number.getCountryCode() + number.getNationalNumber();
			} catch (NumberParseException e) {
				throw new ConstConvertFailureException(this, e);
			}
		};
	};
	final Str2StrConstConverter AVATAR 					= new Str2StrConstConverter(1010, "avatar");
	
	final Str2IntConstConverter TID						= new Str2IntConstConverter(1011, "tid");
	
	final Str2ObjConstConverter<Version> VERSION		= new Str2ObjConstConverter<Version>(1012, "version", Version.V_1_0) {
		@Override
		public Version convert(String k) throws ConstConvertFailureException {
			return Version.match(k);
		}
	};
	
	final Str2StrConstConverter CAPTCHA					= new Str2StrConstConverter(1013, "captcha");
	
	/**
	 * 身份证
	 */
	final Str2StrConstConverter IDENTITY				= new Str2StrConstConverter(1014, "identity") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isIdentity(value))
				throw new ConstConvertFailureException(this);
			return value;
		};
	};
	
	final Str2IntConstConverter REGION					= new Str2IntConstConverter(1015, "region");
	final Str2IntConstConverter TENANT_REGION			= new Str2IntConstConverter(1016, "tenantRegion");

	final Str2BoolConstConverter AGREE					= new Str2BoolConstConverter(1018, "agree", false) {
		public Boolean convert(String value) throws ConstConvertFailureException {
			return Boolean.valueOf(value);
		};
	};
	
	final Str2ObjConstConverter<Client> CLIENT			= new Str2ObjConstConverter<Client>(1019, "client", Client.APP) {
		@Override
		public Client convert(String k) throws ConstConvertFailureException {
			Client client = Client.match(Integer.valueOf(k));
			if (null == client)
				throw ConstConvertFailureException.errorConstException(this);
			return client;
		}
	};
	final Str2StrConstConverter PWD						= new Str2StrConstConverter(1020, "pwd") {
		public String convert(String value) throws ConstConvertFailureException {
			return DigestUtils.md5Hex(value);
		};
	};
	final Str2IntConstConverter UID						= new Str2IntConstConverter(1021, "uid");
	final Str2IntConstConverter ID						= new Str2IntConstConverter(1022, "id");
	final Str2IntConstConverter MAX_TENANTS_COUNT		= new Str2IntConstConverter(1023, "maxTenantsCount") {
		public Integer convert(String value) throws ConstConvertFailureException {
			int val = Integer.valueOf(value);
			if (val < 0)
				throw ConstConvertFailureException.errorConstException(this);
			return val;
		};
	};
	
	final Str2ObjConstConverter<Set<Integer>> MODULES	= new Str2ObjConstConverter<Set<Integer>>(1024, "modules") {
		@Override
		public Set<Integer> convert(String k) throws ConstConvertFailureException {
			try {
				return SerializeUtil.JsonUtil.GSON.fromJson(k, INT_SET_TYPE);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
	
	final Str2BoolConstConverter TENANT_ADD_AUTONOMY		= new Str2BoolConstConverter(1025, "tenantAddAutonomy", false) {
		public Boolean convert(String value) throws ConstConvertFailureException {
			return Boolean.valueOf(value);
		};
	};
	
	final Str2ObjConstConverter<EmployeeState> EMPLOYEE_STATE	= new Str2ObjConstConverter<EmployeeState>(1026, "employeeState") {
		public EmployeeState convert(String value) throws ConstConvertFailureException {
			EmployeeState state = EmployeeState.match(Integer.valueOf(value));
			if (null == state)
				throw ConstConvertFailureException.errorConstException(this);
			return state;
		};
	};
	
	final Str2ObjConstConverter<AppState> APP_STATE	= new Str2ObjConstConverter<AppState>(1027, "appState") {
		public AppState convert(String value) throws ConstConvertFailureException {
			AppState state = AppState.match(Integer.valueOf(value));
			if (null == state)
				throw ConstConvertFailureException.errorConstException(this);
			return state;
		};
	};
	
	final Str2IntConstConverter PAGE					= new Str2IntConstConverter(1100, "page", 1);
	final Str2IntConstConverter PAGE_SIZE				= new Str2IntConstConverter(1101, "pageSize", 10);

	final Str2ObjConstConverter<NonAutoCategory> NON_AUTO_CATEGORY			= new Str2ObjConstConverter<NonAutoCategory>(1200, "nonAutoCategory") {
		@Override
		public NonAutoCategory convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, NonAutoCategory.class);
		}
	};

	final Str2ObjConstConverter<EmployeeSearcher> EMPLOYEE_SEARCHER			= new Str2ObjConstConverter<EmployeeSearcher>(1201, "employeeSearch") {
		@Override
		public EmployeeSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, EmployeeSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<NonAutoProductSearcher> NON_AUTO_PRODUCT_SEARCHER = new Str2ObjConstConverter<NonAutoProductSearcher>(1202, "nonAutoProductSearch") {
		@Override
		public NonAutoProductSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, NonAutoProductSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<EmployeeInfo> EMPLOYEE_INFO			= new Str2ObjConstConverter<EmployeeInfo>(1202, "employeeInfo") {
		@Override
		public EmployeeInfo convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, EmployeeInfo.class);
		}
	};
	
	final Str2ObjConstConverter<UserSearcher> USER_SEARCHER			= new Str2ObjConstConverter<UserSearcher>(1203, "userSearch") {
		@Override
		public UserSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, UserSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<AppSearcher> APP_SEARCHER			= new Str2ObjConstConverter<AppSearcher>(1204, "appSearch") {
		@Override
		public AppSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, AppSearcher.class);
		}
	};
}
