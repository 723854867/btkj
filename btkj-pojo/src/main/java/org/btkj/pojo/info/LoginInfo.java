package org.btkj.pojo.info;

import org.btkj.pojo.entity.User;

public class LoginInfo extends MainPageInfo {

	private static final long serialVersionUID = 895363411400533215L;

	private String token;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, User user) {
		super(user);
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
