package org.btkj.web.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.pojo.model.Version;
import org.rapid.util.common.Validator;
import org.rapid.util.common.consts.conveter.Str2BoolConstConverter;
import org.rapid.util.common.consts.conveter.Str2IntConstConverter;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.common.consts.conveter.Str2StrConstConverter;
import org.rapid.util.exception.ConstConvertFailureException;

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
	
	/**
	 * 头部信息是可选的，因此这里不需要 id, global session id
	 */
	final Str2StrConstConverter SESSION_ID				= new Str2StrConstConverter("sessionId");
	
	/**
	 * 每次请求都必须要使用的识别码：能识别出客户端的所属的 app、tenant 以及 user
	 */
	final Str2ObjConstConverter<Credential> CREDENTIAL		= new Str2ObjConstConverter<Credential>(1000, "credential") {
		@Override
		public Credential convert(String credential) throws ConstConvertFailureException {
			try {
				return ParamsUtil.parse(this, credential, true);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
	
	/**
	 * 用户的唯一识别码
	 */
	final Str2ObjConstConverter<Credential> UCODE			= new Str2ObjConstConverter<Credential>(1001, "ucode") {
		@Override
		public Credential convert(String chiefCode) throws ConstConvertFailureException {
			try {
				return ParamsUtil.parse(this, chiefCode, false);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};

	/**
	 * 登陆或者注册时需要传递的参数，用来区分不同的 app、tenant
	 * 
	 */
	final Str2IntConstConverter APP_ID					= new Str2IntConstConverter(1002, "appId", BtkjConsts.APP_ID_BAOTU);
	final Str2IntConstConverter TYPE					= new Str2IntConstConverter(1003, "type");
	final Str2StrConstConverter TOKEN					= new Str2StrConstConverter(1004, "token");
	final Str2StrConstConverter ACTION					= new Str2StrConstConverter(1005, "action");
	
	final Str2StrConstConverter NAME					= new Str2StrConstConverter(1006, "name");
	final Str2StrConstConverter APP_NAME				= new Str2StrConstConverter(1007, "appName");
	final Str2StrConstConverter TENANT_NAME				= new Str2StrConstConverter(1008, "tenantName");

	
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
	final Str2StrConstConverter APPLY_KEY				= new Str2StrConstConverter(1016, "applyKey");
	final Str2BoolConstConverter AGREE					= new Str2BoolConstConverter(1017, "agree", false) {
		public Boolean convert(String value) throws ConstConvertFailureException {
			return Boolean.valueOf(value);
		};
	};
	
	final Str2ObjConstConverter<ClientType> CLIENT_TYPE	= new Str2ObjConstConverter<ClientType>(1018, "clientType", ClientType.APP) {
		@Override
		public ClientType convert(String k) throws ConstConvertFailureException {
			return ClientType.match(Integer.valueOf(k));
		}
	};
	final Str2StrConstConverter PWD						= new Str2StrConstConverter(1019, "pwd") {
		public String convert(String value) throws ConstConvertFailureException {
			return DigestUtils.md5Hex(value);
		};
	};
	final Str2IntConstConverter UID						= new Str2IntConstConverter(1020, "uid");
	final Str2IntConstConverter ID						= new Str2IntConstConverter(1021, "id");
	
	final Str2IntConstConverter PAGE					= new Str2IntConstConverter(1100, "page", 1);
	final Str2IntConstConverter PAGE_SIZE				= new Str2IntConstConverter(1101, "pageSize", 10);
}
