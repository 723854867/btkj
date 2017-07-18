package org.btkj.web.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.courier.model.QuotaNoticeSubmit;
import org.btkj.pojo.bo.Version;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.NonAutoCategory;
import org.btkj.pojo.po.NonAutoProduct;
import org.btkj.pojo.vo.AppSearcher;
import org.btkj.pojo.vo.ArticleSearcher;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.pojo.vo.NonAutoProductSearcher;
import org.btkj.pojo.vo.QuizSearcher;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.TenantSettingsSubmit;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.rapid.util.common.consts.conveter.Str2BoolConstConverter;
import org.rapid.util.common.consts.conveter.Str2IntConstConverter;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.common.consts.conveter.Str2StrConstConverter;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.rapid.util.validator.Validator;

import com.google.gson.reflect.TypeToken;

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
	final Str2StrConstConverter NAME					= new Str2StrConstConverter(1006, "name");
	
	/**
	 * 租户名
	 */
	final Str2StrConstConverter TNAME					= new Str2StrConstConverter(1007, "tname");
	
	final Str2IntConstConverter CUSTOMER_ID				= new Str2IntConstConverter(1008, "customerId");

	
	/**
	 * 需要严格检查手机的格式，返回的是 "+" + 国家编号 + 手机号码，比如 +8613888888888
	 */
	final Str2StrConstConverter MOBILE					= new Str2StrConstConverter(1009, "mobile") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isMobile(value, Locale.CHINA.getCountry()))
				throw ConstConvertFailureException.errorConstException(this);
			return value;
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
	
	final Str2StrConstConverter ADDRESS 					= new Str2StrConstConverter(1013, "address");
	
	final Str2StrConstConverter CAPTCHA					= new Str2StrConstConverter(1014, "captcha");
	
	/**
	 * 身份证
	 */
	final Str2StrConstConverter IDENTITY				= new Str2StrConstConverter(1015, "identity") {
		public String convert(String value) throws ConstConvertFailureException {
			if (!Validator.isIdentity(value))
				throw ConstConvertFailureException.errorConstException(this);
			return value;
		};
	};
	
	final Str2IntConstConverter REGION					= new Str2IntConstConverter(1016, "region");
	final Str2IntConstConverter TENANT_REGION			= new Str2IntConstConverter(1017, "tenantRegion");
	
	final Str2StrConstConverter TITLE					= new Str2StrConstConverter(1018, "title");

	final Str2BoolConstConverter AGREE					= new Str2BoolConstConverter(1019, "agree", false) {
		public Boolean convert(String value) throws ConstConvertFailureException {
			return Boolean.valueOf(value);
		};
	};
	
	final Str2ObjConstConverter<Client> CLIENT			= new Str2ObjConstConverter<Client>(1020, "client", Client.APP) {
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
	final Str2StrConstConverter PWD						= new Str2StrConstConverter(1021, "pwd") {
		public String convert(String value) throws ConstConvertFailureException {
			return DigestUtils.md5Hex(value);
		};
	};
	final Str2IntConstConverter UID						= new Str2IntConstConverter(1022, "uid");
	final Str2IntConstConverter ID						= new Str2IntConstConverter(1023, "id");
	final Str2IntConstConverter MAX_TENANTS_COUNT		= new Str2IntConstConverter(1024, "maxTenantsCount") {
		public Integer convert(String value) throws ConstConvertFailureException {
			int val = Integer.valueOf(value);
			if (val < 0)
				throw ConstConvertFailureException.errorConstException(this);
			return val;
		};
	};
	final Str2IntConstConverter MAX_ARTICLES_COUNT		= new Str2IntConstConverter(1025, "maxArticlesCount") {
		public Integer convert(String value) throws ConstConvertFailureException {
			int val = Integer.valueOf(value);
			if (val < 0)
				throw ConstConvertFailureException.errorConstException(this);
			return val;
		};
	};
	
	final Str2ObjConstConverter<Set<Integer>> MODULES	= new Str2ObjConstConverter<Set<Integer>>(1026, "modules") {
		@Override
		public Set<Integer> convert(String k) throws ConstConvertFailureException {
			try {
				return SerializeUtil.JsonUtil.GSON.fromJson(k, INT_SET_TYPE);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
	
	final Str2ObjConstConverter<DeliveryType> DELIVERY_TYPE	= new Str2ObjConstConverter<DeliveryType>(1027, "deliveryType") {
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
	
	final Str2StrConstConverter IDENTITY_FACE						= new Str2StrConstConverter(1028, "identityFace");
	final Str2StrConstConverter IDENTITY_BACK						= new Str2StrConstConverter(1029, "identityBack");
	final Str2IntConstConverter IDX									= new Str2IntConstConverter(1030, "idx");
	final Str2StrConstConverter NON_AUTO_BIND						= new Str2StrConstConverter(1031, "nonAutoBind");
	
	final Str2StrConstConverter ICON					= new Str2StrConstConverter(1032, "icon");
	final Str2StrConstConverter LINK					= new Str2StrConstConverter(1033, "link");
	final Str2IntConstConverter MOD						= new Str2IntConstConverter(1034, "mod");
	final Str2IntConstConverter BEGIN_TIME				= new Str2IntConstConverter(1035, "beginTime") {
		public Integer convert(String value) throws ConstConvertFailureException {
			return (int) (DateUtil.getTime(value, DateUtil.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault()) / 1000);
		};
	};
	
	final Str2IntConstConverter END_TIME				= new Str2IntConstConverter(1036, "endTime") {
		public Integer convert(String value) throws ConstConvertFailureException {
			return (int) (DateUtil.getTime(value, DateUtil.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault()) / 1000);
		};
	};
	
	final Str2StrConstConverter ORDER_ID						= new Str2StrConstConverter(1037, "orderId");
	
	final Str2ObjConstConverter<VehicleOrderSearcher> VEHICLE_ORDER_SEARCHER = new Str2ObjConstConverter<VehicleOrderSearcher>(1038, "vehicleOrderSearcher") {
		@Override
		public VehicleOrderSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, VehicleOrderSearcher.class);
		}
	};
	final Str2ObjConstConverter<BonusSearcher> BONUS_SEARCHER = new Str2ObjConstConverter<BonusSearcher>(1039, "bonusSearcher") {
		@Override
		public BonusSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, BonusSearcher.class);
		}
	};	
	
	final Str2StrConstConverter VEHICLE_ID						= new Str2StrConstConverter(1040, "vehicleId");
	final Str2StrConstConverter AGENT							= new Str2StrConstConverter(1041, "agent");
	final Str2StrConstConverter KEY								= new Str2StrConstConverter(1042, "key");
	final Str2IntConstConverter NUM								= new Str2IntConstConverter(1043, "num");
	final Str2StrConstConverter SERVICE_PHONE					= new Str2StrConstConverter(1044, "servicePhone");
	final Str2IntConstConverter DEPTH							= new Str2IntConstConverter(1045, "depth");
	final Str2IntConstConverter MIN								= new Str2IntConstConverter(1046, "min");
	final Str2IntConstConverter MAX								= new Str2IntConstConverter(1047, "max");
	
	final Str2ObjConstConverter<TenantSettingsSubmit> TENANT_SETTINGS_SUBMIT = new Str2ObjConstConverter<TenantSettingsSubmit>(1048, "tenantSettingsSubmit") {
		@Override
		public TenantSettingsSubmit convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, TenantSettingsSubmit.class);
		}
	};
	
	final Str2StrConstConverter JIAN_JIE_ID						= new Str2StrConstConverter(1049, "jianJieId");
	
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
	
	final Str2StrConstConverter BIZ_NO						= new Str2StrConstConverter(1106, "bizNo");
	final Str2IntConstConverter QUOTE_GROUP					= new Str2IntConstConverter(1107, "quoteGroup");
	final Str2IntConstConverter INSURE_GROUP				= new Str2IntConstConverter(1108, "insureGroup"); 
	
	final Str2ObjConstConverter<QuotaNoticeSubmit> QUOTA_NOTICE_SUBMIT	= new Str2ObjConstConverter<QuotaNoticeSubmit>(1109, "quotaNoticeSubmit") {
		@Override
		public QuotaNoticeSubmit convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, QuotaNoticeSubmit.class);
		}
	};
	
	final Str2ObjConstConverter<CustomerSearcher> CUSTOMER_SEARCHER	= new Str2ObjConstConverter<CustomerSearcher>(1110, "customerSearcher") {
		@Override
		public CustomerSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, CustomerSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<NonAutoCategory> NON_AUTO_CATEGORY			= new Str2ObjConstConverter<NonAutoCategory>(1200, "nonAutoCategory") {
		@Override
		public NonAutoCategory convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, NonAutoCategory.class);
		}
	};

	final Str2ObjConstConverter<EmployeeSearcher> EMPLOYEE_SEARCHER			= new Str2ObjConstConverter<EmployeeSearcher>(1201, "employeeSearcher") {
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
	
	final Str2ObjConstConverter<NonAutoProduct> NON_AUTO_PRODUCT = new Str2ObjConstConverter<NonAutoProduct>(1203, "nonAutoProduct") {
		@Override
		public NonAutoProduct convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, NonAutoProduct.class);
		}
	};
	
	final Str2ObjConstConverter<UserSearcher> USER_SEARCHER			= new Str2ObjConstConverter<UserSearcher>(1205, "userSearch") {
		@Override
		public UserSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, UserSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<AppSearcher> APP_SEARCHER			= new Str2ObjConstConverter<AppSearcher>(1206, "appSearch") {
		@Override
		public AppSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, AppSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<AppPO> APP_INFO			= new Str2ObjConstConverter<AppPO>(1207, "appInfo") {
		@Override
		public AppPO convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, AppPO.class);
		}
	};
	final Str2ObjConstConverter<TenantSearcher> TENANT_SEARCHER	= new Str2ObjConstConverter<TenantSearcher>(1208, "tenantSearcher") {
		@Override
		public TenantSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, TenantSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<ArticleSearcher> ARTICLE_SEARCHER			= new Str2ObjConstConverter<ArticleSearcher>(1210, "articleSearcher") {
		@Override
		public ArticleSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, ArticleSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<QuizSearcher> QUIZ_SEARCHER				= new Str2ObjConstConverter<QuizSearcher>(1211, "quizSearcher") {
		@Override
		public QuizSearcher convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, QuizSearcher.class);
		}
	};
	
	final Str2ObjConstConverter<CRUD_TYPE> CRUD_TYPE = new Str2ObjConstConverter<CRUD_TYPE>(1212, "crudType") {
		@Override
		public CRUD_TYPE convert(String k) throws ConstConvertFailureException {
			int value;
			try {
				value = Integer.valueOf(k);
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
			CRUD_TYPE type = org.rapid.util.common.enums.CRUD_TYPE.match(value);
			if (null == type)
				throw ConstConvertFailureException.errorConstException(this);
			return type;
		}
	};	
	
	final Str2ObjConstConverter<CoefficientType> COEFFICIENT_TYPE = new Str2ObjConstConverter<CoefficientType>(1213, "coefficientType") {
		@Override
		public CoefficientType convert(String k) throws ConstConvertFailureException {
			int value;
			try {
				value = Integer.valueOf(k);
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
			CoefficientType type = CoefficientType.match(value);
			if (null == type)
				throw ConstConvertFailureException.errorConstException(this);
			return type;
		}
	};
	
	final Str2ObjConstConverter<ComparisonSymbol> COMPARISON = new Str2ObjConstConverter<ComparisonSymbol>(1214, "comparison") {
		@Override
		public ComparisonSymbol convert(String k) throws ConstConvertFailureException {
			int value;
			try {
				value = Integer.valueOf(k);
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
			ComparisonSymbol type = ComparisonSymbol.match(value);
			if (null == type)
				throw ConstConvertFailureException.errorConstException(this);
			return type;
		}
	};
	
	final Str2ObjConstConverter<String[]> ARRAY	= new Str2ObjConstConverter<String[]>(1215, "array") {
		@Override
		public String[] convert(String k) throws ConstConvertFailureException {
			return SerializeUtil.JsonUtil.GSON.fromJson(k, new TypeToken<String[]>(){}.getType());
		}
	};
	
	final Str2ObjConstConverter<Lane> LANE = new Str2ObjConstConverter<Lane>(1216, "lane") {
		@Override
		public Lane convert(String k) throws ConstConvertFailureException {
			int value;
			try {
				value = Integer.valueOf(k);
			} catch (NumberFormatException e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
			Lane type = Lane.match(value);
			if (null == type)
				throw ConstConvertFailureException.errorConstException(this);
			return type;
		}
	};	
}
