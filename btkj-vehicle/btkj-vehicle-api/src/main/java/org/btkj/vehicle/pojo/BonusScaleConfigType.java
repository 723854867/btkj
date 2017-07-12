package org.btkj.vehicle.pojo;

public enum BonusScaleConfigType {

	PROFIT(1),
	
	NO_PROFIT(2);
	
	private int mark;
	
	private BonusScaleConfigType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final BonusScaleConfigType match(int type) {
		for (BonusScaleConfigType temp : BonusScaleConfigType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
