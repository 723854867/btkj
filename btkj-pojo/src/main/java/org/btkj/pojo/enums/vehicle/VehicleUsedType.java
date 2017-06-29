package org.btkj.pojo.enums.vehicle;

import org.btkj.pojo.enums.UnitType;

/**
 * 车辆使用性质
 * 
 * @author ahab
 */
public enum VehicleUsedType {
	
	/**
	 * 家庭自用客车
	 */
	HOME_USE("homeUse", "家庭自用客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL};
		}
	},
	
	/**
	 * 企业客车
	 */
	ENTERPRISE("enterprise", "企业客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 党政机关、事业客车
	 */
	ORGAN("organ", "党政机关、事业客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.OFFICE};
		}
	},
	
	/**
	 * 出租租匿客车
	 */
	LEASE("lease", "出租租匿客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL, UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 城市公交客车
	 */
	CITY_BUS("cityBus", "城市公交客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 公路客运客车
	 */
	HIGHWAY_TRANSPORT("highwayTransport", "公路客运客车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 营业货车
	 */
	BIZ_TRUCK("bizTruck", "营业货车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL, UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 非营业货车
	 */
	NO_BIZ_TRUCK("noBizTruck", "非营业货车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL, UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 特种车
	 */
	PARTICULAR("particular", "特种车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.ENTERPRISE};
		}
	},
	
	/**
	 * 摩托车
	 */
	MOTOR("motor", "摩托车") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL};
		}
	},
	
	/**
	 * 拖拉机
	 */
	TRACTOR("tractor", "拖拉机") {
		@Override
		public UnitType[] supportUnitTypes() {
			return new UnitType[]{UnitType.PERSONAL};
		}
	};
	
	private String id;
	private String title;
	
	private VehicleUsedType(String id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public String id() {
		return id;
	}
	
	public String title() {
		return title;
	}
	
	public abstract UnitType[] supportUnitTypes(); 
	
	public static final VehicleUsedType match(String name) {
		for (VehicleUsedType usedType : VehicleUsedType.values()) {
			if (usedType.id.equals(name))
				return usedType;
		}
		return null;
	}
	
	public static final VehicleUsedType[] supportTypes() {
		return new VehicleUsedType[]{HOME_USE, ENTERPRISE, ORGAN};
	}
}
