package org.btkj.pojo.enums;

public enum LogScoreType {

	/**
	 * 车险-个人积分奖励
	 */
	VEHICLE_PERSONAL(1),
	
	/**
	 * 车险-管理积分奖励
	 */
	VEHICLE_MANAGE(2),
	
	/**
	 * 车险-规模积分奖励
	 */
	VEHICLE_SCALE(4);
	
	private int mark;
	
	private LogScoreType(int mark) {
		this.mark = mark;
	}
	
	public int mark() { 
		return mark;
	}
}
