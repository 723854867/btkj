package org.btkj.pojo.vo;

import java.io.Serializable;

import org.btkj.pojo.po.UserPO;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -8394028501904858733L;

	private String token;
	private UserDetailTips user;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, UserPO user) {
		this.token = token;
		this.user = new UserDetailTips(user);
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
	
	public void setUser(UserDetailTips user) {
		this.user = user;
	}
}
