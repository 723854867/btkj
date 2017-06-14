package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

public class Insurance implements Serializable  {

	private static final long serialVersionUID = -3953709949421529511L;

	private double quota;
	private double price;
	
	public Insurance() {}
	
	public Insurance(double quota) {
		this.quota = quota;
	}
	
	public Insurance(double quota, double price) {
		this.quota = quota;
		this.price = price;
	}
	
	public double getQuota() {
		return quota;
	}

	public void setQuota(double quota) {
		this.quota = quota;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
