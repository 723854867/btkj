package org.btkj.pojo.enums;

public enum VehicleUnitType {

	/**
	 * 个人
	 */
	PERSONAL(1, "个人"),
	
	/**
	 * 企业
	 */
	ENTERPRISE(2, "企业"),
	
	/**
	 * 机关
	 */
	OFFICE(4, "机关");
	
	private int mark;
	private String title;
	
	private VehicleUnitType(int mark, String title) {
		this.mark = mark;
		this.title = title;
	}
	
	public String title() {
		return title;
	}
	
	public int mark() {
		return mark;
	}
}
