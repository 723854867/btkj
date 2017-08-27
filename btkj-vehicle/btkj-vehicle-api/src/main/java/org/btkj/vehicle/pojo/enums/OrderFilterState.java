package org.btkj.vehicle.pojo.enums;

/**
 * 车险订单过滤状态：用来查询订单
 * 
 * @author ahab
 *
 */
public enum OrderFilterState {

	/**
	 * 核保
	 */
	INSURE(1),
	
	/**
	 * 未核保
	 */
	QUOTE_SUCCESS(2),
	
	/**
	 * 核保失败
	 */
	INSURE_FAILURE(3),
	
	/**
	 * 核保中
	 */
	INSURING(4),
	
	/**
	 * 出单
	 */
	ISSUE(5),
	
	/**
	 * 待预约
	 */
	INSURE_SUCCESS(6),
	
	/**
	 * 待出单
	 */
	ISSUE_SUCCESS(7),
	
	/**
	 * 已出单
	 */
	ISSUED(8);
	
	private int mark;
	
	private OrderFilterState(int state) {
		this.mark = state;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final OrderFilterState match(int state) {
		for (OrderFilterState temp : OrderFilterState.values()) {
			if (temp.mark == state)
				return temp;
		}
		return null;
	}
}
