package org.btkj.manager.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.enums.VehicleBonusType;
import org.btkj.pojo.info.EmployeeTip;

public class BonusScaleWaterInfo implements Serializable {

	private static final long serialVersionUID = 5272761902486517280L;

	private String name;
	private int employeeId;
	private int time;
	private int quota;
	private VehicleBonusType bonusType;
	private int insurerId;
	private String insurerName;
	private String license;

	public BonusScaleWaterInfo(EmployeeTip et, VehiclePolicy policy, Insurer insurer) {
		this.name = null != et ? et.getName() : null;
		this.employeeId = policy.getSalesmanId();
		this.time = policy.getIssuanceTime();
		this.quota = policy.commercialQuotaInCent() + policy.compulsoryQuotaInCent();
		this.bonusType = policy.getBonusType();
		this.insurerId = policy.getInsurerId();
		this.insurerName = insurer.getName();
		this.license = policy.getLicense();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public VehicleBonusType getBonusType() {
		return bonusType;
	}

	public void setBonusType(VehicleBonusType bonusType) {
		this.bonusType = bonusType;
	}

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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
}
