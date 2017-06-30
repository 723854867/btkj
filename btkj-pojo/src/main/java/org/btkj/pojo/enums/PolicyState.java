package org.btkj.pojo.enums;

public enum PolicyState {

	/**
	 * 系统错误
	 */
	SYSTEM_ERROR(1),
	
	/**
	 * 报价中
	 */
	QUOTING(2),
	
	/**
	 * 报价失败
	 */
	QUOTE_FAILURE(4),
	
	/**
	 * 报价成功
	 */
	QUOTE_SUCCESS(8),
	
	/**
	 * 核保中
	 */
	INSURING(16),
	
	/**
	 * 报价成功，投保失败
	 */
	QUOTE_SUCCESS_INSURE_FAILURE(32),
	
	/**
	 * 投保成功
	 */
	INSURE_SUCCESS(64),
	
	/**
	 * 已预约
	 */
	ENGAGED(128),
	
	/**
	 * 已出单
	 */
	ISSUED(256);
	
	private int mark;
	
	private PolicyState(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final PolicyState match(String state) { 
		for (PolicyState temp : PolicyState.values()) {
			if (temp.name().equals(state))
				return temp;
		}
		return null;
	}
}
