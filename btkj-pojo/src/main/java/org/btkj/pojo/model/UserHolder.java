package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;

public class UserHolder implements Serializable {

	private static final long serialVersionUID = 5978024854104605939L;

	private AppPO app;
	private UserPO user;
	
	public UserHolder() {}
	
	public UserHolder(AppPO app, UserPO user) {
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
