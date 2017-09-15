package org.btkj.login.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.user.App;

public class AppInfo implements Serializable {

	private static final long serialVersionUID = 8912689798294031317L;

	private int appId;
	private String name;
	
	public AppInfo() {}
	
	public AppInfo(App app) {
		this.appId = app.getId();
		this.name = app.getName();
	}
	
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
