package org.btkj.vehicle.cache.domain;

import org.rapid.util.common.model.UniqueModel;

public class CfgCoefficientRange implements UniqueModel<Integer> {

	private static final long serialVersionUID = -4547084511418743765L;

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

	@Override
	public Integer key() {
		return this.id;
	}
}
