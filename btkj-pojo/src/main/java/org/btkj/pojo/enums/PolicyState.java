package org.btkj.pojo.enums;

public enum PolicyState {

	/**
	 * 新建报价
	 */
	NEW,
	
	/**
	 * 报价成功
	 */
	QUOTE_SUCCESS,
	
	/**
	 * 报价失败
	 */
	QUOTE_FAILURE,
	
	/**
	 * 报价成功，投保失败
	 */
	QUOTE_SUCCESS_INSURE_FAILURE,
	
	/**
	 * 投保成功
	 */
	INSURE_SUCCESS,
	
	/**
	 * 系统错误
	 */
	SYSTEM_ERROR;
	
	public static final PolicyState match(String state) { 
		for (PolicyState temp : PolicyState.values()) {
			if (temp.name().equals(state))
				return temp;
		}
		return null;
	}
}
