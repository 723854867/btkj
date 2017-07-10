package org.btkj.pojo.enums;

public enum PayType {

	FULL(1),
	
	NET(2),
	
	ADVANCE(4);
	
	private int mark;
	
	private PayType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
