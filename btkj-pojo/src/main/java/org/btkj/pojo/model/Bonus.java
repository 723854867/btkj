package org.btkj.pojo.model;

import java.io.Serializable;

public class Bonus implements Serializable {

	private static final long serialVersionUID = -5138966870907477503L;

	private String commercialBonus;
	private String compulsoryBonus;
	
	public String getCommercialBonus() {
		return commercialBonus;
	}
	
	public void setCommercialBonus(String commercialBonus) {
		this.commercialBonus = commercialBonus;
	}
	
	public String getCompulsoryBonus() {
		return compulsoryBonus;
	}
	
	public void setCompulsoryBonus(String compulsoryBonus) {
		this.compulsoryBonus = compulsoryBonus;
	}
}
