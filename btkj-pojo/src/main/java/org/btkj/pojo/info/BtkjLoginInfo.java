package org.btkj.pojo.info;

import org.btkj.pojo.entity.User;

public class BtkjLoginInfo extends BtkjMainPageInfo {

	private static final long serialVersionUID = 3091869699899383379L;

	private String token;
	
	public BtkjLoginInfo() {}
	
	public BtkjLoginInfo(String token, User user) {
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
