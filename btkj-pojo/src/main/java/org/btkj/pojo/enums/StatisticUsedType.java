package org.btkj.pojo.enums;

/**
 * 统计使用性质：共统计使用
 * 
 * @author ahab
 */
public enum StatisticUsedType {

	BIZ_COACH(1),						// 营利客车
	BIZ_TRUCK(2),						// 营利货车
	NO_BIZ_COACH(4),					// 非营利客车
	NO_BIZ_TRUCK(8),					// 非营利货车
	OTHER(16);							// 其他
	
	private int mark;
	
	private StatisticUsedType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
