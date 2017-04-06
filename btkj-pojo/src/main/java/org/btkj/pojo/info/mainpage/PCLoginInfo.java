package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;

public class PCLoginInfo extends PCMainPageInfo implements ILoginInfo {
	
	private static final long serialVersionUID = -6137369188159345031L;
	
	private String token;
	
	public PCLoginInfo() {}
	
	public PCLoginInfo(String token, User user) {
		super(user);
		this.token = token;
	}

	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}
}
