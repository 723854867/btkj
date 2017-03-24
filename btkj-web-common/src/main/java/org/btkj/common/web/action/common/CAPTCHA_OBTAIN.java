package org.btkj.common.web.action.common;

import org.btkj.common.web.ParamUtil;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.Action;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.Credential;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN extends Action {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		CaptchaReceiver receiver = ParamUtil.captchaReceiver(request, credential);
		Result<String> result = courierService.captchaObtain(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
}
