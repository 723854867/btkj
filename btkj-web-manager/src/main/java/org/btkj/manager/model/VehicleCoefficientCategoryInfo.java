package org.btkj.manager.model;

import java.io.Serializable;

import org.btkj.pojo.enums.VehicleCoefficientCategory;

public class VehicleCoefficientCategoryInfo implements Serializable {

	private static final long serialVersionUID = -267823267253323219L;

	private int id;
	private String name;
	
	public VehicleCoefficientCategoryInfo(VehicleCoefficientCategory category) {
		this.id = category.mark();
		this.name = category.title();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
