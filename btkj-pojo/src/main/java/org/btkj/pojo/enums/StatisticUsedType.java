package org.btkj.pojo.enums;

/**
 * 统计使用性质：共统计使用
 * 
 * @author ahab
 */
public enum StatisticUsedType {

	BIZ(1),								// 营利
	NO_BIZ(2),							// 非营利
	OTHER(4);							// 其它
	
	private int mark;
	
	private StatisticUsedType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
