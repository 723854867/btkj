package org.btkj.pojo.enums;

import org.btkj.pojo.enums.VehicleTypeCode;

/**
 * 车辆种类
 * 
 * @author ahab
 */
public enum VehicleType {

	/**
	 * 客车
	 */
	COACH("1") {
		@Override
		public VehicleTypeCode[] typeCode() {
			return new VehicleTypeCode[]{VehicleTypeCode.A012, VehicleTypeCode.A022, VehicleTypeCode.A032, VehicleTypeCode.A052};
		}
	},
	
	/**
	 * 货车
	 */
	TRUCK("2") {
		@Override
		public VehicleTypeCode[] typeCode() {
			return new VehicleTypeCode[]{VehicleTypeCode.B012, VehicleTypeCode.B022, VehicleTypeCode.B032, VehicleTypeCode.B042, 
					VehicleTypeCode.B052, VehicleTypeCode.B062, VehicleTypeCode.B101, VehicleTypeCode.B102, VehicleTypeCode.B103, 
					VehicleTypeCode.B104, VehicleTypeCode.B105, VehicleTypeCode.B201, VehicleTypeCode.B202, VehicleTypeCode.B203, 
					VehicleTypeCode.B204, VehicleTypeCode.B205, VehicleTypeCode.C112, VehicleTypeCode.C122, VehicleTypeCode.C132, 
					VehicleTypeCode.C142};
		}
	},
	
	/**
	 * 特种车
	 */
	PARTICULAR("3") {
		@Override
		public VehicleTypeCode[] typeCode() {
			return new VehicleTypeCode[]{VehicleTypeCode.B142, VehicleTypeCode.C022, VehicleTypeCode.C032, VehicleTypeCode.C042, 
					VehicleTypeCode.C112, VehicleTypeCode.C122, VehicleTypeCode.C132, VehicleTypeCode.C142, VehicleTypeCode.C152, 
					VehicleTypeCode.C162, VehicleTypeCode.C172, VehicleTypeCode.C182};
		}
	},
	
	/**
	 * 摩托车
	 */
	MOTOR("4") {
		@Override
		public VehicleTypeCode[] typeCode() {
			return new VehicleTypeCode[]{VehicleTypeCode.D012, VehicleTypeCode.D022, VehicleTypeCode.D032};
		}
	},
	
	/**
	 * 拖拉机
	 */
	TRACTOR("5") {
		@Override
		public VehicleTypeCode[] typeCode() {
			return new VehicleTypeCode[]{VehicleTypeCode.E012, VehicleTypeCode.E022, VehicleTypeCode.E112, VehicleTypeCode.E122, 
					VehicleTypeCode.E201, VehicleTypeCode.E202, VehicleTypeCode.E203, VehicleTypeCode.E204, VehicleTypeCode.E205, 
					VehicleTypeCode.E301, VehicleTypeCode.E302, VehicleTypeCode.E303, VehicleTypeCode.E304, VehicleTypeCode.E305};
		}
	};
	
	private String id;
	
	private VehicleType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public abstract VehicleTypeCode[] typeCode();
	
	public static final VehicleType match(String type) {
		for (VehicleType temp : VehicleType.values()) {
			if (temp.id.equals(type))
				return temp;
		}
		return null;
	}
}
