package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

public class PolicyDetail implements Serializable {

	private static final long serialVersionUID = 6319047453189138048L;

	private String commercialNo;
	private String compulsiveNo;
	private double commercialRate;
	private double compulsiveRate;

	public String getCommercialNo() {
		return commercialNo;
	}

	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}

	public String getCompulsiveNo() {
		return compulsiveNo;
	}

	public void setCompulsiveNo(String compulsiveNo) {
		this.compulsiveNo = compulsiveNo;
	}

	public double getCommercialRate() {
		return commercialRate;
	}

	public void setCommercialRate(double commercialRate) {
		this.commercialRate = commercialRate;
	}

	public double getCompulsiveRate() {
		return compulsiveRate;
	}

	public void setCompulsiveRate(double compulsiveRate) {
		this.compulsiveRate = compulsiveRate;
	}
}
