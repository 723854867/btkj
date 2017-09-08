package org.btkj.pojo.model;

import java.io.Serializable;

public class ScoreReward implements Serializable {

	private static final long serialVersionUID = 2741173326613330874L;

	private int quota;
	private int type;
	
	public int getQuota() {
		return quota;
	}
	
	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
