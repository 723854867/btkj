package org.btkj.pojo.model;

import java.io.Serializable;

public class Insurance implements Serializable  {

	private static final long serialVersionUID = -3953709949421529511L;

	private String quota;
	private String price;
	
	public Insurance() {}
	
	public Insurance(String quota) {
		this.quota = quota;
	}
	
	public Insurance(String quota, String price) {
		this.quota = quota;
		this.price = price;
	}
	
	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
