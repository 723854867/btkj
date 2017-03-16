package org.btkj.user.model;

import java.io.Serializable;

public class TokenReplaceModel implements Serializable {

	private static final long serialVersionUID = -7453671661787242850L;

	private String lockId;
	private String token;
	
	public TokenReplaceModel(String lockId, String token) {
		this.lockId = lockId;
		this.token = token;
	}

	public String getLockId() {
		return lockId;
	}
	
	public String getToken() {
		return token;
	}
}
