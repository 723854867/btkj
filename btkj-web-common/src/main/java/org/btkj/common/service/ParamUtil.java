package org.btkj.common.service;

import org.btkj.common.web.Request;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaReceiver.Type;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.web.util.Params;
import org.rapid.util.exception.ConstConvertFailureException;

public class ParamUtil {

	public static final CaptchaReceiver captchaReceiver(Request request) {
		int appId = request.getOptionalParam(Params.APP_ID);
		String mobile = request.getParam(Params.MOBILE);
		Type type = Type.match(request.getOptionalParam(Params.TYPE));
		if (null == type)
			throw new ConstConvertFailureException(Params.TYPE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setIdentity(mobile);
		receiver.setType(type);
		receiver.setAppId(appId);
		return receiver;
	}
	
	public static final CaptchaVerifier captchaVerifier(Request request) {
		int appId = request.getOptionalParam(Params.APP_ID);
		String mobile = request.getParam(Params.MOBILE);
		Type type = Type.match(request.getOptionalParam(Params.TYPE));
		String captcha = request.getParam(Params.CAPTCHA);
		if (null == type)
			throw new ConstConvertFailureException(Params.TYPE);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setIdentity(mobile);
		verifier.setType(type);
		verifier.setAppId(appId);
		verifier.setCaptcha(captcha);
		return verifier;
	}
}
