package org.btkj.web.util;

import javax.validation.Validation;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Version;
import org.rapid.util.common.consts.conveter.str.Str2IntConstConverter;
import org.rapid.util.common.consts.conveter.str.Str2ObjConstConverter;
import org.rapid.util.common.consts.conveter.str.Str2StrConstConverter;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.exception.ConstConvertFailureException;

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
	
	final Str2StrConstConverter TOKEN					= new Str2StrConstConverter(1004, "token");
	final Str2StrConstConverter ACTION					= new Str2StrConstConverter(1005, "action");
	
	final Str2ObjConstConverter<Version> VERSION		= new Str2ObjConstConverter<Version>(1013, "version", Version.V_1_0) {
		@Override
		public Version convert(String k) throws ConstConvertFailureException {
			return Version.match(k);
		}
	};
	
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
