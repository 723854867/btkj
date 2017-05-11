package org.btkj.pojo.enums;

public enum PayType {

	/**
	 * 全额支付
	 */
	GROSS(0),
	
	/**
	 * 净保费支付
	 * 
	 */
	NET(1),
	
	/**
	 * 公司垫付
	 * 
	 */
	ADVANCES(2);
	
	private int mark;
	private PayType(int mark) {
		this.mark = mark;
	}
	public int mark() {
		return mark;
	}
}
