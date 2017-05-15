package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 保途平台员工列表信息
 * 
 * @author sj
 */
public class UserListInfo implements Serializable {

	private static final long serialVersionUID = -1079996680418465118L;

	private int uid;
	private String mobile;
	private int appLoginTime;
	private int created;
	private int appId;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getAppLoginTime() {
		return appLoginTime;
	}
	public void setAppLoginTime(int appLoginTime) {
		this.appLoginTime = appLoginTime;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}

}
