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
	COMMERCIAL_NO_PROFIT(8);
	
	private int mark;
	
	private BonusManageConfigType(int mark) {
		this.mark = mark;
	}
}
