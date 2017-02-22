package org.btkj.common.web.action;

import org.btkj.common.web.Beans;
import org.btkj.common.web.IAction;
import org.btkj.common.web.Params;
import org.btkj.common.web.session.HttpSession;
import org.btkj.courier.pojo.model.CaptchaReceiver;
import org.btkj.courier.pojo.model.CaptchaReceiver.Type;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN implements IAction {

	@Override
	public Result<String> execute(HttpSession session) {
		String mobile = session.getParam(Params.MOBILE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setIdentity(mobile);
		receiver.setType(Type.MOBILE);
		Result<String> result = Beans.courierService.captchaObtan(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
}
