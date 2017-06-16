package org.btkj.pojo.enums;

public enum PolicyState {

	/**
	 * 新建报价
	 */
	NEW(1),
	
	/**
	 * 报价成功
	 */
	QUOTE_SUCCESS(2),
	
	/**
	 * 报价失败
	 */
	QUOTE_FAILURE(4),
	
	/**
	 * 报价成功，投保失败
	 */
	QUOTE_SUCCESS_INSURE_FAILURE(8),
	
	/**
	 * 投保成功
	 */
	INSURE_SUCCESS(16),
	
	/**
	 * 已预约
	 */
	ENGAGED(32),
	
	/**
	 * 已出单
	 */
	ISSUED(64),
	
	/**
	 * 系统错误
	 */
	SYSTEM_ERROR(128);
	
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
