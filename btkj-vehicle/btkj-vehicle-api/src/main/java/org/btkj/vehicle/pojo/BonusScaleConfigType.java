package org.btkj.vehicle.pojo;

public enum BonusScaleConfigType {

	PROFIT(1),
	
	NO_PROFIT(2);
	
	private int mark;
	
	private BonusScaleConfigType(int mark) {
		this.mark = mark;
	}
}
