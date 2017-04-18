package org.btkj.user.model;

import java.io.Serializable;

public class TokenRemoveModel implements Serializable {

	private static final long serialVersionUID = 2439079642484869541L;

	private int uid;
	private String lockId;
	
	public TokenRemoveModel() {}
	
	public TokenRemoveModel(int uid, String lockId) {
		this.uid = uid;
		this.lockId = lockId;
	}

	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getLockId() {
		return lockId;
	}
	
	public void setLockId(String lockId) {
		this.lockId = lockId;
	}
}
