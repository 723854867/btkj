package org.btkj.user.model;

import java.io.Serializable;

public class CreateMarker implements Serializable {

	private static final long serialVersionUID = -662351645923377972L;

	private int appId;
	private String mobile;
	
	public CreateMarker() {}
	
	public CreateMarker(int appId, String mobile) {
		this.appId = appId;
		this.mobile = mobile;
	}

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
