package org.btkj.master.pojo.info;

import java.io.Serializable;

import org.btkj.master.pojo.entity.Admin;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1425212172002340940L;
	
	private int uid;
	private int mod;
	private String name;
	private String token;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, Admin admin) {
		this.token = token;
		this.uid = admin.getId();
		this.mod = admin.getMod();
		this.name = admin.getName();
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
