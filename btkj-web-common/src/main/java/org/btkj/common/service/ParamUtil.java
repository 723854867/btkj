package org.btkj.common.service;

import org.btkj.common.web.Request;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaReceiver.Type;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.rapid.util.exception.ConstConvertFailureException;

public class ParamUtil {

	public static final CaptchaReceiver captchaReceiver(Request request, Credential credential) {
		String mobile = request.getParam(Params.MOBILE);
		Type type = Type.match(request.getOptionalParam(Params.TYPE));
		if (null == type)
			throw new ConstConvertFailureException(Params.TYPE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setAppId(credential.getApp().getId());
		receiver.setIdentity(mobile);
		receiver.setType(type);
		return receiver;
	}
	
	public static final CaptchaVerifier captchaVerifier(Request request, Credential credential) {
		String mobile = request.getParam(Params.MOBILE);
		Type type = Type.match(request.getOptionalParam(Params.TYPE));
		String captcha = request.getParam(Params.CAPTCHA);
		if (null == type)
			throw new ConstConvertFailureException(Params.TYPE);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(credential.getApp().getId());
		verifier.setIdentity(mobile);
		verifier.setType(type);
		verifier.setCaptcha(captcha);
		return verifier;
	}
}
