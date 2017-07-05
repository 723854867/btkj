package org.btkj.vehicle.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class City implements UniqueModel<Integer> {

	private static final long serialVersionUID = -6748716010804985723L;

	private int code;
	private String name;
	private int renewalPeriod;				// 续保期限
	private int created;
	private int updated;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getRenewalPeriod() {
		return renewalPeriod;
	}

	public void setRenewalPeriod(int renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
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
		return code;
	}
}
