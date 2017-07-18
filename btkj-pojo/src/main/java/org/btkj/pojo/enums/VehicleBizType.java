package org.btkj.pojo.enums;

/**
 * 汽车营业类型
 * 
 * @author ahab
 */
public enum VehicleBizType {

	/**
	 * 营业
	 */
	PROFIT("profit"),
	
	/**
	 * 非营业
	 */
	NO_PROFIT("noProfit"),
	
	/**
	 * 不区分营业非营业
	 */
	IGNROE_PROFIT("ignoreProfit");
	
	private String id;
	
	private VehicleBizType(String id) {
		this.id = id;
	}
	
	public String id() {
		return id;
	}
}
