package org.btkj.pojo.entity;

import org.btkj.pojo.comparable.Comparable;
import org.rapid.util.common.model.UniqueModel;

public class VehicleCoefficient implements UniqueModel<Long> {

	private static final long serialVersionUID = 3705826374497188616L;

	private long id;
	private int tid;
	private int categoryId;
	private String name;
	private String comparable;
	private String comparableValue;
	private int created;
	private int updated;
	
	public VehicleCoefficient() {}
	
	public VehicleCoefficient(long id, int categoryId, String name, Comparable comparable, String comparableValue) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.comparable = comparable.name();
		this.comparableValue = comparableValue;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComparable() {
		return comparable;
	}

	public void setComparable(String comparable) {
		this.comparable = comparable;
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
	public Long key() {
		return this.id;
	}
}
