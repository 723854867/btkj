package org.btkj.pojo.enums;

/**
 * 车辆使用性质
 * 
 * @author ahab
 */
public enum VehicleUsedType {
	
	/**
	 * 家庭自用车
	 */
	FAMILY(1),
	
	/**
	 * 党政机关、事业团体
	 */
	ORGAN(2),
	
	/**
	 * 非营业企业客车
	 */
	NO_BIZ_ENTERPRISE(3),
	
	/**
	 * 不区分营业非营业
	 */
	BIZ_UNBIZ_DESPITE(4),
	
	/**
	 * 出租租赁
	 */
	LEASE(5),
	
	/**
	 * 营业货车
	 */
	BIZ_TRUCK(6),
	
	/**
	 * 非营业货车
	 */
	NO_BIZ_TRUCK(7),
	
	/**
	 * 城市公交
	 */
	CITY_BUS(8);
	
	private int mark;
	
	private VehicleUsedType(int mark) {
		this.mark = mark;
	}
	
	public static final VehicleUsedType match(int usedType) {
		for (VehicleUsedType type : VehicleUsedType.values()) {
			if (type.mark == usedType)
				return type;
		}
		return null;
	}
}
