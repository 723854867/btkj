package org.btkj.master.action.impl;

import org.btkj.master.action.MasterAction;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN extends MasterAction {

	@Override
	public Result<?> execute(Request request) {
		CaptchaReceiver receiver = _captchaReceiver(request, 0);
		Result<String> result = courierService.captchaObtain(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
	
	private CaptchaReceiver _captchaReceiver(Request request, int appId) {
		String mobile = request.getParam(Params.MOBILE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setAppId(appId);
		receiver.setIdentity(mobile);
		return receiver;
	}
}
