package org.btkj.pojo.model;

import java.io.Serializable;

/**
 * appId、tid、uid 三个属性唯一决定一个用户
 * 
 * @author ahab
 */
public class Identity implements Serializable {

	private static final long serialVersionUID = 2115318493120954038L;

	private int appId;
	private int tid;
	private int uid;
	
	public Identity() {}
	
	public Identity(int appId, int tid, int uid) {
		this.appId = appId;
		this.tid = tid;
		this.uid = uid;
	}

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
