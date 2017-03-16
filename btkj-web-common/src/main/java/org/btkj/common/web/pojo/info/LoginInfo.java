package org.btkj.common.web.pojo.info;

import org.btkj.pojo.entity.User;

/**
 * 和 MainPage 的唯一区别就是多了一个 token
 * 
 * @author ahab
 *
 */
public class LoginInfo extends MainPageInfo {

	private static final long serialVersionUID = 205112271436593191L;

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
