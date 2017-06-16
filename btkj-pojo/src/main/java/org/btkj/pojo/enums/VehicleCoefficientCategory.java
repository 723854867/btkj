package org.btkj.pojo.enums;

public enum VehicleCoefficientCategory {

	/**
	 * 出险系数
	 */
	CLAIM(1, "出险系数"),
	
	/**
	 * 无赔系数
	 */
	NO_CLAIM(2, "无赔系数"),
	
	/**
	 * 年龄系数
	 */
	AGE(4, "年龄系数"),
	
	/**
	 * 性别系数
	 */
	GENDER(8, "性别系数"),
	
	/**
	 * 车龄系数
	 */
	VEHICLE_AGE(16, "车龄系数"),
	
	/**
	 * 车牌系数
	 */
	LICENSE(32, "车牌系数"),
	
	/**
	 * 转续保系数
	 */
	ZHUAN_XU_BAO(64, "转续保系数"),
	
	/**
	 * 新车购置价系数
	 */
	PRICE(128, "新车购置价系数"),
	
	/**
	 * 座位系数
	 */
	SEAT_COUNT(256, "座位系数");
	
	private int mark;
	private String title;
	
	private VehicleCoefficientCategory(int mark, String title) {
		this.mark = mark;
		this.title = title;
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
}
