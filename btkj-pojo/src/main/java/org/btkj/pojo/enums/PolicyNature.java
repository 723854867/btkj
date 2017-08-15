package org.btkj.pojo.enums;

public enum PolicyNature {

	/**
	 * 新车
	 */
	NEW(1),
	
	/**
	 * 转保
	 */
	RE_INSURANCE(2),
	
	/**
	 * 续保
	 */
	RENEWAL(4);
	
	private int mark;
	
	private PolicyNature(int mark) {
		this.mark = mark;
	}
	
	public static final PolicyNature match(int mark) {
		for (PolicyNature temp : PolicyNature.values()) {
			if (temp.mark == mark)
				return temp;
		}
		return null;
	}
}
