package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;

public class ManagerLoginInfo extends ManagerMainPageInfo implements ILoginInfo {

	private static final long serialVersionUID = -869469237838267868L;

	private String token;
	
	public ManagerLoginInfo() {}
	
	public ManagerLoginInfo(String token, User user) {
		super(user);
		this.token = token;
	}
	
	@Override
	public String getToken() {
		return token;
	}
	
	@Override
	public void setToken(String token) {
		this.token = token;
	}
}
