package org.btkj.pojo.enums;

/**
 * 险种
 * 
 * @author ahab
 *
 */
public enum InsuranceType {

	COMMERCIAL(1, "商业"),

	COMPULSORY(2, "交强");
	
	private int mark;
	private String title;
	
	private InsuranceType(int mark, String title) {
		this.mark = mark;
		this.title = title;
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
}
