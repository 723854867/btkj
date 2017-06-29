package org.btkj.pojo.enums;

public enum UnitType {

	/**
	 * 个人
	 */
	PERSONAL("个人"),
	
	/**
	 * 企业
	 */
	ENTERPRISE("企业"),
	
	/**
	 * 机关
	 */
	OFFICE("机关");
	
	private String title;
	
	private UnitType(String title) {
		this.title = title;
	}
	
	public String title() {
		return title;
	}
}
