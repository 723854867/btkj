package org.btkj.login.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.validator.custom.Mobile;

public class CaptchaObtainParam extends Param {

	private static final long serialVersionUID = -3907331674832398842L;

	@Min(0)
	private int appId;
	@NotNull
	@Mobile
	private String mobile;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
