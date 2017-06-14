package org.btkj.pojo.enums;

/**
 * 车险类型
 * 
 * @author ahab
 */
public enum VehiclePolicyType {

	商业险(1),
	
	交强险(2);
	
	private int mark;
	
	private VehiclePolicyType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
}
