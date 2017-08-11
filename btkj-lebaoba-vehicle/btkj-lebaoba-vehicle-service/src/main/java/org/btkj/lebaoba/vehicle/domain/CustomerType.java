package org.btkj.lebaoba.vehicle.domain;

import org.btkj.pojo.enums.VehicleUnitType;

public enum CustomerType {

	/**
	 * 个人
	 */
	PERSONAL("01"),
	
	/**
	 * 企业
	 */
	ENTERPRISE("03"),
	
	/**
	 * 机关
	 */
	OFFICE("02");
	
	private String mark;
	
	private CustomerType(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
	
	public static final CustomerType convert(VehicleUnitType unitType) { 
		if (null == unitType)
			return null;
		switch (unitType) {
		case PERSONAL:
			return PERSONAL;
		case ENTERPRISE:
			return CustomerType.ENTERPRISE;
		case OFFICE:
			return CustomerType.OFFICE;
		default:
			return null;
		}
	}
}
