package org.btkj.statistics.pojo;

public enum PerformanceCategory {

	/**
	 * 车险
	 * 
	 */
	VEHICLE(1),
	
	/**
	 * 非车险
	 * 
	 */
	NONAUTO(2),
	
	/**
	 * 礼品商城
	 * 
	 */
	GIFT_MALL(4);
	
	private int mark;
	
	private PerformanceCategory(int mark) {
		this.mark = mark;
	}
	
	public int mark() { 
		return mark;
	}
}
