package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class User implements UniqueModel<Integer> {

	private static final long serialVersionUID = 524781447550935468L;
	
	private int uid;
	private int appId;
	private String mobile;
	private String pwd;
	private String avatar;
	private int appLoginTime;			
	private int pcLoginTime;
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
