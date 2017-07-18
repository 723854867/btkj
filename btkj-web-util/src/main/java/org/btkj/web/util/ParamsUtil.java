package org.btkj.web.util;

import org.btkj.pojo.bo.CaptchaReceiver;
import org.btkj.pojo.bo.CaptchaVerifier;

public class ParamsUtil {
	
	public static final CaptchaReceiver captchaReceiver(Request request, int appId) {
		String mobile = request.getParam(Params.MOBILE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setAppId(appId);
		receiver.setIdentity(mobile);
		return receiver;
	}
	
	public static final CaptchaVerifier captchaVerifier(Request request, int appId) {
		String mobile = request.getParam(Params.MOBILE);
		String captcha = request.getParam(Params.CAPTCHA);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(appId);
		verifier.setIdentity(mobile);
		verifier.setCaptcha(captcha);
		return verifier;
	}
	
	public static final CaptchaVerifier captchaVerifier(Request request, String mobile, int appId) {
		String captcha = request.getParam(Params.CAPTCHA);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(appId);
		verifier.setIdentity(mobile);
		verifier.setCaptcha(captcha);
		return verifier;
	}
}
