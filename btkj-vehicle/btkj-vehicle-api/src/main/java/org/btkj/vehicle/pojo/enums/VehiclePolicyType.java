package org.btkj.vehicle.pojo.enums;

/**
 * 车险保单类型
 * 
 * @author ahab
 */
public enum VehiclePolicyType {

	/**
	 * 外部的保单，不在保途云平台上生成的保单
	 */
	EXTERNAL(1), 
	
	/**
	 * 商户自己销售的保单
	 */
	TENANT_SELF(2),
	
	/**
	 * 其他商户挂靠销售的保单
	 */
	TENANT_OTHER(4);
	
	private int mark;
	
	private VehiclePolicyType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
