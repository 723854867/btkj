package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;

public class User implements Entity<Integer> {

	private static final long serialVersionUID = 524781447550935468L;
	
	private int uid;
	private int appId;
	private String mobile;
	private String pwd;
	private String identity;
	private String name;
	private String avatar;
	private int appLoginTime;			
	private int pcLoginTime;
	private int managerLoginTime;
	private int created;
	private int updated;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public int getAppLoginTime() {
		return appLoginTime;
	}
	
	public void setAppLoginTime(int appLoginTime) {
		this.appLoginTime = appLoginTime;
	}
	
	public int getPcLoginTime() {
		return pcLoginTime;
	}
	
	public void setPcLoginTime(int pcLoginTime) {
		this.pcLoginTime = pcLoginTime;
	}
	
	public int getManagerLoginTime() {
		return managerLoginTime;
	}
	
	public void setManagerLoginTime(int managerLoginTime) {
		this.managerLoginTime = managerLoginTime;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return uid;
	}
}
