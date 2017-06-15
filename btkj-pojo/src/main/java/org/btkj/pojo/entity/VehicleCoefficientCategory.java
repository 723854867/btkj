package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class VehicleCoefficientCategory implements UniqueModel<Integer> {

	private static final long serialVersionUID = -8789155873652730748L;

	private int id;
	private String name;
	private int maxCustomCount;
	private int created;
	private int updated;

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

	public int getMaxCustomCount() {
		return maxCustomCount;
	}

	public void setMaxCustomCount(int maxCustomCount) {
		this.maxCustomCount = maxCustomCount;
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
