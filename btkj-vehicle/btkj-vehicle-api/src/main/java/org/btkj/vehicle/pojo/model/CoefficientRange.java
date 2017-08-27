package org.btkj.vehicle.pojo.model;

import java.io.Serializable;

public class CoefficientRange implements Serializable {

	private static final long serialVersionUID = 6449746889324474050L;

	private int id;
	private String name;
	private int comparison;
	private String comparableValue;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
