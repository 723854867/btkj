package org.btkj.pojo.info.user;

import java.io.Serializable;

import org.btkj.pojo.entity.UserPO;

public class UserPagingInfo implements Serializable {

	private static final long serialVersionUID = 1993858725755535044L;

	private int uid;
	private int mod;
	private String name;
	private String avatar;
	private String mobile;
	private int pcLoginTime;
	private int appLoginTime;
	private int created;
	private int updated;
	
	public UserPagingInfo() {}
	
	public UserPagingInfo(UserPO user) {
		this.uid = user.getUid();
		this.mod = user.getMod();
		this.name = user.getName();
		this.avatar = user.getAvatar();
		this.mobile = user.getMobile();
		this.pcLoginTime = user.getPcLoginTime();
		this.appLoginTime = user.getAppLoginTime();
		this.created = user.getCreated();
		this.updated = user.getUpdated();
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getPcLoginTime() {
		return pcLoginTime;
	}

	public void setPcLoginTime(int pcLoginTime) {
		this.pcLoginTime = pcLoginTime;
	}

	public int getAppLoginTime() {
		return appLoginTime;
	}

	public void setAppLoginTime(int appLoginTime) {
		this.appLoginTime = appLoginTime;
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
}
