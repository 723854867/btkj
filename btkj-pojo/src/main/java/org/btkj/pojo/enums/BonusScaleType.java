package org.btkj.pojo.enums;

public enum BonusScaleType {
	
	/**
	 * 非营利客车
	 */
	NO_PROFIT_COACH(1),
	
	/**
	 * 非营利货车
	 */
	NO_PROFIT_TRUCK(2),
	
	/**
	 * 营利客车
	 */
	PROFT_COACH(4),
	
	/**
	 * 营利货车
	 */
	PROFIT_TRUCK(8),
	
	/**
	 * 其他
	 */
	OTHER(16);
	
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
