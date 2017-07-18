package org.btkj.pojo.bo;

import java.io.Serializable;

import org.btkj.pojo.po.UserPO;

/**
 * 保途模式的用户登录信息
 * 
 * @author ahab
 */
public class BtkjUserLoginModel implements Serializable {

	private static final long serialVersionUID = -8326544043927519822L;
	
	private UserPO user;
	private String token;
	
	public BtkjUserLoginModel() {}
	
	public BtkjUserLoginModel(UserPO user, String token) {
		this.user = user;
		this.token = token;
	}
	
	public UserPO getUser() {
		return user;
	}
	
	public void setUser(UserPO user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
