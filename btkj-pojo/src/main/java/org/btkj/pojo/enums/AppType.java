package org.btkj.pojo.enums;

public enum AppType {

	BTKJ(1),
	
	ISOLATE(2);
	
	private int mark;
	
	private AppType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
