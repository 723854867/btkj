package org.btkj.pojo.enums;

/**
 * 分组类型
 * 
 * @author ahab
 */
public enum GroupType {

	APP(1),
	USER(2),
	TENANT(4),
	INSURER(8),
	EMPLOYEE(16);
	
	private int mod;
	
	private GroupType(int mod) {
		this.mod = mod;
	}
	
	public int mod() {
		return mod;
	}
}
