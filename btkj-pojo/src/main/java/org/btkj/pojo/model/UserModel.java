package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;

public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 4096557016770882472L;

	private App app;
	private User user;
	
	public UserModel() {
	}
	
	public UserModel(App app, User user) {
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
