package org.btkj.pojo.bo;

public class CaptchaVerifier extends CaptchaReceiver {

	private static final long serialVersionUID = -9208929521328249746L;

	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
