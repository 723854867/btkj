package org.btkj.pojo.enums;

public enum ActType {

	// 报价成功
	VEHICLE_QUOTE_SUCCESS(1),
	// 已出单
	VEHICLE_POLICY_ISSUED(2);
	
	private int mark;
	
	private ActType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final ActType match(int mark) {
		for (ActType temp : ActType.values()) {
			if (temp.mark == mark)
				return temp;
		}
		return null;
	}
}
