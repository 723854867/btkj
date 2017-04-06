package org.btkj.master.pojo.info;

import org.btkj.master.service.Role;

public class LoginInfo extends MainPageInfo {

	private static final long serialVersionUID = -9222238050861282310L;
	
	private String token;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, Role role) {
		super(role);
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
