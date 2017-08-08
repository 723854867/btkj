package org.btkj.config.pojo;

public enum TarType {

	ADMIN(1),
	
	APP(2),
	
	USER(4),
	
	TENANT(8),
	
	EMPLOYEE(16);
	
	private int mark;
	
	private TarType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
