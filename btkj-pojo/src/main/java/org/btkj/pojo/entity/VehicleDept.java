package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class VehicleDept implements UniqueModel<Integer> {

	private static final long serialVersionUID = 2703691147214245149L;

	private int id;
	private int brandId;
	private String name;
	private int created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
