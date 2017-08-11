package org.btkj.pojo.bo;

import java.io.Serializable;
import java.util.Map;

import org.btkj.pojo.enums.CommercialInsuranceType;

public class PolicySchema implements Serializable {

	private static final long serialVersionUID = -8472548988615869922L;

	private double commericialTotal;			// 商业险总计保费
	private String commercialStart;				// 商业险起保时间
	private String commercialEnd;				// 商业险到期时间
	
	private double compulsiveTotal;				// 交强险总额(90天之外的取不到，值为0)
	private double vehicleVesselTotal;			// 车船税总额(90天之外的取不到，值为0)
	private String compulsiveStart;				// 交强险起保时间
	private String compulsiveEnd;				// 交强险到期时间
	
	private String noLossDiscountRate;			// 无赔款优惠系数
	
	private Map<CommercialInsuranceType, Insurance> insurances;
	
	public double getCompulsiveTotal() {
		return compulsiveTotal;
	}
	
	public void setCompulsiveTotal(double compulsiveTotal) {
		this.compulsiveTotal = compulsiveTotal;
	}
	
	public double getVehicleVesselTotal() {
		return vehicleVesselTotal;
	}
	
	public void setVehicleVesselTotal(double vehicleVesselTotal) {
		this.vehicleVesselTotal = vehicleVesselTotal;
	}
	
	public double getCommericialTotal() {
		return commericialTotal;
	}
	
	public void setCommericialTotal(double commericialTotal) {
		this.commericialTotal = commericialTotal;
	}

	public String getCommercialStart() {
		return commercialStart;
	}

	public void setCommercialStart(String commercialStart) {
		this.commercialStart = commercialStart;
	}

	public String getCommercialEnd() {
		return commercialEnd;
	}

	public void setCommercialEnd(String commercialEnd) {
		this.commercialEnd = commercialEnd;
	}

	public String getCompulsiveStart() {
		return compulsiveStart;
	}

	public void setCompulsiveStart(String compulsiveStart) {
		this.compulsiveStart = compulsiveStart;
	}

	public String getCompulsiveEnd() {
		return compulsiveEnd;
	}

	public void setCompulsiveEnd(String compulsiveEnd) {
		this.compulsiveEnd = compulsiveEnd;
	}
	
	public String getNoLossDiscountRate() {
		return noLossDiscountRate;
	}

	public void setNoLossDiscountRate(String noLossDiscountRate) {
		this.noLossDiscountRate = noLossDiscountRate;
	}

	public Map<CommercialInsuranceType, Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(Map<CommercialInsuranceType, Insurance> insurances) {
		this.insurances = insurances;
	}
}
