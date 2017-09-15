package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;

public class UserHolder implements Serializable {

	private static final long serialVersionUID = 5978024854104605939L;

	private App app;
	private User user;
	
	public UserHolder() {}
	
	public UserHolder(App app, User user) {
		this.app = app;
		this.user = user;
	}
	
	public App getApp() {
		return app;
	}
	
	public void setApp(App app) {
		this.app = app;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
