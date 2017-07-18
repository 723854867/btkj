package org.btkj.pojo.bo;

import java.io.Serializable;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;

public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 4096557016770882472L;

	private AppPO app;
	private UserPO user;
	
	public UserModel() {}
	
	public UserModel(AppPO app, UserPO user) {
		this.app = app;
		this.user = user;
	}
	
	public AppPO getApp() {
		return app;
	}
	
	public void setApp(AppPO app) {
		this.app = app;
	}
	
	public UserPO getUser() {
		return user;
	}
	
	 public void setUser(UserPO user) {
		this.user = user;
	}
}
