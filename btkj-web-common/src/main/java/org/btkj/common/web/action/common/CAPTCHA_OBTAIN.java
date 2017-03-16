package org.btkj.common.web.action.common;

import org.btkj.common.service.ParamUtil;
import org.btkj.common.web.Beans;
import org.btkj.common.web.action.CommonAction;
import org.btkj.common.web.session.UserSession;
import org.btkj.pojo.model.CaptchaReceiver;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN extends CommonAction {

	@Override
	public Result<String> execute(UserSession session) {
		CaptchaReceiver receiver = ParamUtil.captchaReceiver(session);
		Result<String> result = Beans.courierService.captchaObtain(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
}
