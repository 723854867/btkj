package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -8394028501904858733L;

	private int uid;
	private String token;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, int uid) {
		this.uid = uid;
		this.token = token;
	}
	
	public LoginInfo(String token, User user) {
		this.token = token;
		this.uid = user.getUid();
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
