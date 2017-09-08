package org.btkj.manager.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.BonusScale;
import org.btkj.pojo.model.BonusScale.State;

public class BonusScaleInfo implements Serializable {

	private static final long serialVersionUID = 3607284224579584606L;

	private String key;
	private String name;
	private int employeeId;
	private int quota;
	private int SCQuota;
	private int RCQuota;
	private int CMRate;
	private int CPRate;
	private State state;
	
	public BonusScaleInfo(EmployeeTip et, BonusScale scale) {
		this.name = null != et ? et.getName() : null;
		this.employeeId = scale.getEmployeeId();
		this.quota = scale.getQuota();
		this.SCQuota = scale.getSCQuota();
		this.RCQuota = scale.getRCQuota();
		this.CMRate = scale.getCmRate();
		this.CPRate = scale.getCpRate();
		this.state = scale.getState();
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
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

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getSCQuota() {
		return SCQuota;
	}

	public void setSCQuota(int sCQuota) {
		SCQuota = sCQuota;
	}

	public int getRCQuota() {
		return RCQuota;
	}

	public void setRCQuota(int rCQuota) {
		RCQuota = rCQuota;
	}

	public int getCMRate() {
		return CMRate;
	}

	public void setCMRate(int cMRate) {
		CMRate = cMRate;
	}

	public int getCPRate() {
		return CPRate;
	}

	public void setCPRate(int cPRate) {
		CPRate = cPRate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
