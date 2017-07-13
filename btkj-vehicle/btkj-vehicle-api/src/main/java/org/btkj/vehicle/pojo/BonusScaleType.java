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
	
	public static final boolean illegalMod(int mod) {
		int cmod = 0;
		for (BonusScaleType type : BonusScaleType.values())
			cmod |= type.mark;
		return (cmod & mod) == mod;
	}
	
	public int mark() {
		return mark;
	}
}
