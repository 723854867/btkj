package org.btkj.common.service;

import org.btkj.common.web.session.UserSession;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.CaptchaReceiver.Type;
import org.btkj.web.util.Params;
import org.rapid.util.exception.ConstConvertFailureException;

public class ParamUtil {

	public static final CaptchaReceiver captchaReceiver(UserSession session) {
		int appId = session.getOptionalParam(Params.APP_ID);
		String mobile = session.getParam(Params.MOBILE);
		Type type = Type.match(session.getOptionalParam(Params.TYPE));
		if (null == type)
			throw new ConstConvertFailureException(Params.TYPE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setIdentity(mobile);
		receiver.setType(type);
		receiver.setAppId(appId);
		return receiver;
	}
	
	public static final CaptchaVerifier captchaVerifier(UserSession session) {
		int appId = session.getOptionalParam(Params.APP_ID);
		String mobile = session.getParam(Params.MOBILE);
		Type type = Type.match(session.getOptionalParam(Params.TYPE));
		String captcha = session.getParam(Params.CAPTCHA);
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
