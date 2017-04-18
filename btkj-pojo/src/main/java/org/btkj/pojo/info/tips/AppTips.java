package org.btkj.pojo.info.tips;

import java.io.Serializable;

public class AppTips implements Serializable {

	private static final long serialVersionUID = 8912689798294031317L;

	private int appId;
	private String name;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
