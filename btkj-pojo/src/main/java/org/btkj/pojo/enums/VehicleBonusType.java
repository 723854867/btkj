package org.btkj.pojo.enums;

/**
 * 车辆奖励计算类型
 * 
 * @author ahab
 */
public enum VehicleBonusType {

	PC(1),						// 营利客车
	PT(2),						// 营利货车
	NPC(4),						// 非营利客车
	NPT(8),						// 非营利客车
	OTHER(16);					// 其他
	
	private int mark;
	private VehicleBonusType(int mark) {
		this.mark = mark;
	}
	public int mark() {
		return mark;
	}
	public static final VehicleBonusType match(int type) {
		for (VehicleBonusType temp : VehicleBonusType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
