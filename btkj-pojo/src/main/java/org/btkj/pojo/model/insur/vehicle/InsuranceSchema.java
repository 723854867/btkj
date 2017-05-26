package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

/**
 * 保险方案
 * 
 * @author ahab
 */
public class InsuranceSchema implements Serializable {

	private static final long serialVersionUID = -5009683705023012208L;

	private int insurerId;							// 保险公司ID
	private String insurerName;						// 保险公司名字
	private CompulsiveInsurance compulsive;			// 交强险/车船税
	private CommercialInsurance commercial;			// 商业险
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public String getInsurerName() {
		return insurerName;
	}
	
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	
	public CompulsiveInsurance getCompulsive() {
		return compulsive;
	}
	
	public void setCompulsive(CompulsiveInsurance compulsive) {
		this.compulsive = compulsive;
	}
	
	public CommercialInsurance getCommercial() {
		return commercial;
	}
	
	public void setCommercial(CommercialInsurance commercial) {
		this.commercial = commercial;
	}
}
