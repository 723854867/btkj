package org.btkj.vehicle.pojo;

public enum BonusScaleType {
	
	PARTICULAR(1),

	PROFT_COACH(2),
	
	PROFIT_TRUCK(4),
	
	NO_PROFIT_COACH(8),
	
	NO_PROFIT_TRUCK(16);
	
	private int mark;
	
	private BonusScaleType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
