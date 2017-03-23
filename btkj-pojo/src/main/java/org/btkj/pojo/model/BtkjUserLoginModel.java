package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

/**
 * 保途模式的用户登录信息
 * 
 * @author ahab
 */
public class BtkjUserLoginModel implements Serializable {

	private static final long serialVersionUID = -8326544043927519822L;
	
	private User user;
	private String token;
	
	public BtkjUserLoginModel() {}
	
	public BtkjUserLoginModel(User user, String token) {
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
