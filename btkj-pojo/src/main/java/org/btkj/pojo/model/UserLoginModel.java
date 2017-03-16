package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

public class UserLoginModel implements Serializable {

	private static final long serialVersionUID = -8326544043927519822L;
	
	private User user;
	private String token;
	
	public UserLoginModel() {}
	
	public UserLoginModel(User user, String token) {
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
