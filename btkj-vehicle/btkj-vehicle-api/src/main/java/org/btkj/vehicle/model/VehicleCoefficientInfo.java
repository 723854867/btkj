package org.btkj.vehicle.model;

import java.io.Serializable;

import org.btkj.pojo.entity.VehicleCoefficient;

public class VehicleCoefficientInfo implements Serializable {

	private static final long serialVersionUID = 6914791117434891151L;

	private int id;
	private int type;
	private String name;
	private int comparison;
	private String comparableValue;
	private Integer rate;
	
	public VehicleCoefficientInfo() {}
	
	public VehicleCoefficientInfo(VehicleCoefficient coefficient, Integer rate) {
		this.rate = rate;
		this.id = coefficient.getId();
		this.type = coefficient.getType();
		this.name = coefficient.getName();
		this.comparison = coefficient.getComparison();
		this.comparableValue = coefficient.getComparableValue();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getComparison() {
		return comparison;
	}

	public void setComparison(int comparison) {
		this.comparison = comparison;
	}

	public String getComparableValue() {
		return comparableValue;
	}

	public void setComparableValue(String comparableValue) {
		this.comparableValue = comparableValue;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
}
