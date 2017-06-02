package org.btkj.master.pojo.info;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1425212172002340940L;
	
	private int uid;
	private String token;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, int uid) {
		this.uid = uid;
		this.token = token;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
