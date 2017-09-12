package org.btkj.pojo.param.user;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.AppParam;
import org.rapid.util.validator.custom.Mobile;

public class LoginParam extends AppParam {

	private static final long serialVersionUID = -2705852021176253004L;

	private String pwd;
	@Mobile
	@NotNull
	private String mobile;
	private String captcha;
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
