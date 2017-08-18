package org.btkj.lebaoba.vehicle.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.btkj.lebaoba.vehicle.domain.internal.LogUser;

@XmlRootElement(name = "VehicleAuditPolicy")
public class AuditSubmit {
	
	private String cmNo;
	private String cpNo;
	private String PolicyNo;
	private LogUser logUser;

	public AuditSubmit() {}
	
	public AuditSubmit(String username, String password, String policyNo, String cmNo, String cpNo) {
		this.cmNo = cmNo;
		this.cpNo = cpNo;
		this.PolicyNo = policyNo;
		this.logUser = new LogUser(username, password);
	}
	
	@XmlElement(name = "CommercePolicyNo")
	public String getCmNo() {
		return cmNo;
	}
	
	public void setCmNo(String cmNo) {
		this.cmNo = cmNo;
	}
	
	@XmlElement(name = "CompulsoryPolicyNo")
	public String getCpNo() {
		return cpNo;
	}
	
	public void setCpNo(String cpNo) {
		this.cpNo = cpNo;
	}
	
	@XmlElement(name = "logUser")
	public LogUser getLogUser() {
		return logUser;
	}
	
	public void setLogUser(LogUser logUser) {
		this.logUser = logUser;
	}
	
	@XmlElement(name = "PolicyNo")
	public String getPolicyNo() {
		return PolicyNo;
	}
	
	public void setPolicyNo(String policyNo) {
		this.PolicyNo = policyNo;
	}
}
