package org.btkj.statistics.model;

import java.io.Serializable;

public class TeamEmployee implements Serializable {

	private static final long serialVersionUID = 1329689956419382988L;

	private int uid;
	private int employeeId;
	private double amount;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
