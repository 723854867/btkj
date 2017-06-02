package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.UserTips;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -8394028501904858733L;

	private String token;
	private UserTips user;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, User user) {
		this.token = token;
		this.user = new UserTips(user);
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserTips getUser() {
		return user;
	}
	
	public void setUser(UserTips user) {
		this.user = user;
	}
}
