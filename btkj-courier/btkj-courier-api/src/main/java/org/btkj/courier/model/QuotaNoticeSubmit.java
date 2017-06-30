package org.btkj.courier.model;

import java.io.Serializable;

public class QuotaNoticeSubmit implements Serializable {

	private static final long serialVersionUID = 8914379389984041334L;

	private int commercialRate;
	private int compulsoryRate;
	private boolean taxInclude; // 是否包增值税
	private String agentMobile;
	private String agentName;
	private String customerMobile;

	public int getCommercialRate() {
		return commercialRate;
	}

	public void setCommercialRate(int commercialRate) {
		this.commercialRate = commercialRate;
	}

	public int getCompulsoryRate() {
		return compulsoryRate;
	}

	public void setCompulsoryRate(int compulsoryRate) {
		this.compulsoryRate = compulsoryRate;
	}

	public boolean isTaxInclude() {
		return taxInclude;
	}

	public void setTaxInclude(boolean taxInclude) {
		this.taxInclude = taxInclude;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
}
