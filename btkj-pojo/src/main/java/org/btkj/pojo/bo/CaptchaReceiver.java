package org.btkj.pojo.bo;

import java.io.Serializable;

/**
 * 验证码接收设备的信息
 * 
 * @author ahab
 */
public class CaptchaReceiver implements Serializable {

	private static final long serialVersionUID = 3924529610822330871L;

	private int appId;
	private String identity;			// 接收设备的识别号：手机则是手机号，邮箱就是邮箱账号
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
