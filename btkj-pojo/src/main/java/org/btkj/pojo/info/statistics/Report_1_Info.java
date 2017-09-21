package org.btkj.pojo.info.statistics;

import java.io.Serializable;

import org.btkj.pojo.entity.statistics.StatisticAct;
import org.btkj.pojo.enums.ActType;

public class Report_1_Info implements Serializable {

	private static final long serialVersionUID = 8295134809889617239L;

	private int uid;
	private int employeeId;
	private String name;
	private int quoteNum;
	private int issuedNum;
	private double premium;
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuoteNum() {
		return quoteNum;
	}
	
	public void setQuoteNum(int quoteNum) {
		this.quoteNum = quoteNum;
	}
	
	public int getIssuedNum() {
		return issuedNum;
	}
	
	public void setIssuedNum(int issuedNum) {
		this.issuedNum = issuedNum;
	}
	
	public double getPremium() {
		return premium;
	}
	
	public void setPremium(double premium) {
		this.premium = premium;
	}
	
	public void addStatisticAct(StatisticAct act) {
		ActType type = ActType.match(act.getType());
		switch (type) {
		case VEHICLE_POLICY_ISSUED:
			this.issuedNum += 1;
			break;
		case VEHICLE_QUOTE_SUCCESS:
			this.quoteNum += 1;
			break;
		default:
			break;
		}
	}
	
	public void addStatisticPolicy(PolicyStatisticInfo policy) { 
		this.premium += Double.valueOf(policy.getPremium());
	}
}
