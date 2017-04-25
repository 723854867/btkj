package org.btkj.login.model;

public enum AssumeRoleType {

	USER(1);
	
	private int type;
	
	private AssumeRoleType(int type) {
		this.type = type;
	}
	
	public static final AssumeRoleType match(int type) {
		for (AssumeRoleType temp : AssumeRoleType.values()) {
			if (temp.type == type)
				return temp;
		}
		return null;
	}
}
