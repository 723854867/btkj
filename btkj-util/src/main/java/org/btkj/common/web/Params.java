package org.btkj.common.web;

import org.rapid.util.common.consts.conveter.Str2IntConverter;
import org.rapid.util.common.consts.conveter.Str2StrConverter;
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

	final Str2StrConverter TOKEN				= new Str2StrConverter(1000, "token");
	final Str2StrConverter ACTION				= new Str2StrConverter(1001, "action");
	
	final Str2StrConverter NAME					= new Str2StrConverter(1002, "name") {
		public String convert(String value) throws ConstConvertFailureException {
			return null;
		};
	};
	
	/**
	 * 需要严格检查手机的格式，返回的是 "+" + 国家编号 + 手机号码，比如 +8613888888888
	 */
	final Str2StrConverter MOBILE				= new Str2StrConverter(1003, "mobile") {
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
	final Str2StrConverter AVATAR 				= new Str2StrConverter(1004, "avatar");
	
	final Str2IntConverter TID					= new Str2IntConverter(1005, "tid");
}
