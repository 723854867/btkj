package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class VehicleCoefficient implements UniqueModel<Integer> {

	private static final long serialVersionUID = 3705826374497188616L;

	private int id;
	private int tid;
	private int type;
	private String name;
	private int comparison;
	private String comparableValue;
	private int created;
	private int updated;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
