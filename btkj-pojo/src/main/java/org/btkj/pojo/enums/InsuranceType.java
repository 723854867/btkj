package org.btkj.pojo.enums;

/**
 * 险种
 * 
 * @author ahab
 *
 */
public enum InsuranceType {

	COMMERCIAL(1),

	COMPULSORY(2);
	
	private int mark;
	
	private InsuranceType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
