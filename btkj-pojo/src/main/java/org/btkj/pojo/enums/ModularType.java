package org.btkj.pojo.enums;

public enum ModularType {

	/**
	 * 保途云模块
	 */
	BAOTU(1),
	
	/**
	 * 平台模块
	 */
	APP(2),
	
	/**
	 * 商户模块
	 */
	TENANT(4);
	
	private int mark;
	
	private ModularType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
