package org.btkj.manager.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.enums.VehicleBonusType;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.statistics.pojo.entity.LogExploit;

public class BonusScaleDetail implements Serializable {

	private static final long serialVersionUID = 5272761902486517280L;

	private String name;
	private int employeeId;
	private int time;
	private int quota;
	private VehicleBonusType bonusType;
	
	public BonusScaleDetail(EmployeeTip et, LogExploit exploit, VehicleBonusType type) {
		this.name = null != et ? et.getName() : null;
		this.employeeId = exploit.getEmployeeId();
		this.time = exploit.getCreated();
		this.quota = exploit.getQuota();
		this.bonusType = type;
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
}
