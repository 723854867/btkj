package org.btkj.pojo.bo;

import java.io.Serializable;

/**
 * 强制险：分为交强险和车船税
 * 
 * <pre>
 * 如果是续保信息，则 start 为下年的起保日期，end 为交强险到期时间
 * </pre>
 * 
 * @author ahab
 */
public class CompulsiveInsurance implements Serializable {

	private static final long serialVersionUID = 6817420155344376740L;

	private String start;					// 起保日期
	private String end;						// 截止日期
	private Insurance compulsory;			// 交强险
	private Insurance vehicleVesselTax;		// 车船税
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public Insurance getCompulsory() {
		return compulsory;
	}
	
	public void setCompulsory(Insurance compulsory) {
		this.compulsory = compulsory;
	}
	
	public Insurance getVehicleVesselTax() {
		return vehicleVesselTax;
	}
	
	public void setVehicleVesselTax(Insurance vehicleVesselTax) {
		this.vehicleVesselTax = vehicleVesselTax;
	}
}
