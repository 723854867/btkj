package org.btkj.pojo.info;

import java.io.Serializable;

public class AppCreateInfo implements Serializable {

	private static final long serialVersionUID = 2890492992297291046L;

	private int appId;
	private int tid;
	private int uid;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
}
