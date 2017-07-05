package org.btkj.bihu.vehicle.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class BiHuInsurer implements UniqueModel<Integer> {

	private static final long serialVersionUID = -2382774138741727795L;
	
	private int id;
	private String name;
	private int code;
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
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
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