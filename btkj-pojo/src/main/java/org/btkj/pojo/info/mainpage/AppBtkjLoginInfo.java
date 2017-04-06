package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;

public class AppBtkjLoginInfo extends AppBtkjMainPageInfo implements ILoginInfo {

	private static final long serialVersionUID = 3091869699899383379L;

	private String token;
	
	public AppBtkjLoginInfo() {}
	
	public AppBtkjLoginInfo(String token, User user) {
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
