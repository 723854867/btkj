package org.btkj.pojo.bo;

import java.io.Serializable;

public class Bonus implements Serializable {

	private static final long serialVersionUID = -5138966870907477503L;

	private double commercialBonus;
	private double compulsoryBonus;
	
	public double getCommercialBonus() {
		return commercialBonus;
	}
	
	public void setCommercialBonus(double commercialBonus) {
		this.commercialBonus = commercialBonus;
	}
	
	public double getCompulsoryBonus() {
		return compulsoryBonus;
	}
	
	public void setCompulsoryBonus(double compulsoryBonus) {
		this.compulsoryBonus = compulsoryBonus;
	}
}
