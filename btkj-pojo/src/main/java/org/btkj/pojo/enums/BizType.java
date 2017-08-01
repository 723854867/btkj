package org.btkj.pojo.enums;

public enum BizType {

	/**
	 * 车险手续费
	 */
	VEHICLE_BONUS(1),
	
	/**
	 * 车险管理佣金
	 */
	VEHICLE_BOUNS_MANAGE(2),
	
	/**
	 * 车险规模佣金
	 * 
	 */
	VEHICLE_BONUS_SCALE(4);
	
	private int mark;
	
	private BizType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
