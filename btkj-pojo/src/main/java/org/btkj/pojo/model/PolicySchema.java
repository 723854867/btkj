package org.btkj.pojo.model;

import java.io.Serializable;
import java.util.Map;

import org.btkj.pojo.enums.CommercialInsuranceType;

public class PolicySchema implements Serializable {

	private static final long serialVersionUID = -8472548988615869922L;

	private String commercialTotal;				// 商业险总计保费
	private String commercialStart;				// 商业险起保时间
	private String commercialEnd;				// 商业险到期时间
	private String commercialNo;				// 商业险投保单号

	private String compulsoryTotal;				// 交强险总额(90天之外的取不到，值为0)
	private String vehicleVesselTotal;			// 车船税总额(90天之外的取不到，值为0)
	private String compulsoryStart;				// 交强险起保时间
	private String compulsoryEnd;				// 交强险到期时间
	private String compulsoryNo;				// 交强险投保单号
	
	private String noLossDiscountRate;			// 无赔款优惠系数
	
	private Map<CommercialInsuranceType, Insurance> insurances;

	public String getCommercialTotal() {
		return commercialTotal;
	}
	
	public void setCommercialTotal(String commercialTotal) {
		this.commercialTotal = commercialTotal;
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

	public String getCommercialNo() {
		return commercialNo;
	}

	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}

	public String getCompulsoryTotal() {
		return compulsoryTotal;
	}

	public void setCompulsoryTotal(String compulsoryTotal) {
		this.compulsoryTotal = compulsoryTotal;
	}

	public String getVehicleVesselTotal() {
		return vehicleVesselTotal;
	}

	public void setVehicleVesselTotal(String vehicleVesselTotal) {
		this.vehicleVesselTotal = vehicleVesselTotal;
	}

	public String getCompulsoryStart() {
		return compulsoryStart;
	}

	public void setCompulsoryStart(String compulsoryStart) {
		this.compulsoryStart = compulsoryStart;
	}

	public String getCompulsoryEnd() {
		return compulsoryEnd;
	}

	public void setCompulsoryEnd(String compulsoryEnd) {
		this.compulsoryEnd = compulsoryEnd;
	}

	public String getCompulsoryNo() {
		return compulsoryNo;
	}

	public void setCompulsoryNo(String compulsoryNo) {
		this.compulsoryNo = compulsoryNo;
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
