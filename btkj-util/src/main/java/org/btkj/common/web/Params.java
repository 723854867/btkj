package org.btkj.common.web;

import org.rapid.util.common.consts.StrConst;
import org.rapid.util.common.consts.conveter.Str2StrConverter;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * btkj 的所有请求参数
 * 
 * @author ahab
 *
 */
public interface Params {

	final StrConst TOKEN						= new StrConst(1000, "token");
	final StrConst ACTION						= new StrConst(1001, "action");
	
	final Str2StrConverter NAME					= new Str2StrConverter(1002, "name") {
		public String convert(String value) throws ConstConvertFailureException {
			return null;
		};
	};
	
	final StrConst MOBILE						= new StrConst(1003, "mobile");
	final Str2StrConverter AVATAR 				= new Str2StrConverter(1004, "avatar");
}
