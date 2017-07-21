package org.btkj.vehicle.pojo;

public enum BonusManageConfigType {

	/**
	 * 营利车商业险
	 */
	COMMERCIAL_PROFIT(1),
	
	/**
	 * 营利车交强险
	 */
	COMPULSORY_PROTFIT(2),
	
	/**
	 * 非营利车交强险
	 */
	COMPULSORY_NO_PROFIT(4),
	
	/**
	 * 非盈利车商业险
	 */
	COMMERCIAL_NO_PROFIT(8),
	
	/**
	 * 其他
	 */
	OTHER(16);
	
	private int mark;
	
	private BonusManageConfigType(int mark) {
		this.mark = mark;
	}

	public int mark() {
		return mark;
	}
	
	public static final BonusManageConfigType match(int type) {
		for (BonusManageConfigType temp : BonusManageConfigType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
