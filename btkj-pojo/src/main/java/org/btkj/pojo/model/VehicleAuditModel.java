package org.btkj.pojo.model;

import java.io.Serializable;

public class VehicleAuditModel implements Serializable {

	private static final long serialVersionUID = 7508397742317681011L;

	private String commercialNo;
	private String compulsoryNo;
	
	public VehicleAuditModel() {}
	
	public VehicleAuditModel(String commercialNo, String compulsoryNo) {
		this.commercialNo = commercialNo;
		this.compulsoryNo = compulsoryNo;
	}
	
	public String getCommercialNo() {
		return commercialNo;
	}
	
	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}
	
	public String getCompulsoryNo() {
		return compulsoryNo;
	}
	
	public void setCompulsoryNo(String compulsoryNo) {
		this.compulsoryNo = compulsoryNo;
	}
}
