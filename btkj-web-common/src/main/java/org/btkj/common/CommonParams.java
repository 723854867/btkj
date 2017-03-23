package org.btkj.common;

import org.btkj.pojo.model.Credential;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.exception.ConstConvertFailureException;

public interface CommonParams {

	/**
	 * 每次请求都必须要使用的识别码：能识别出客户端的所属的 app、tenant 以及 user
	 */
	final Str2ObjConstConverter<Credential> CREDENTIAL		= new Str2ObjConstConverter<Credential>(1500, "credential") {
		@Override
		public Credential convert(String credential) throws ConstConvertFailureException {
			try {
				return InternalUtils.parse(credential);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
	
	/**
	 * 用户的唯一识别码
	 */
	final Str2ObjConstConverter<Credential> UCODE			= new Str2ObjConstConverter<Credential>(1501, "ucode") {
		@Override
		public Credential convert(String chiefCode) throws ConstConvertFailureException {
			try {
				return InternalUtils.parse(chiefCode);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(this);
			}
		}
	};
}
