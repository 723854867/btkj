package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;

public class AppLoginInfo extends AppMainPageInfo implements ILoginInfo {

	private static final long serialVersionUID = 895363411400533215L;

	private String token;
	
	public AppLoginInfo() {}
	
	public AppLoginInfo(String token, User user) {
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
