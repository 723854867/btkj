package org.btkj.bihu.vehicle.persistence.entity;

import org.rapid.util.common.model.UniqueModel;

public class CityCode implements UniqueModel<Integer> {

	private static final long serialVersionUID = 2112678389121538395L;

	private int code;
	private int cid;
	private String name;
	private int created;
	private int updated;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
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
	
	public int getUpdated() {
		return updated;
	}
	
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	
	@Override
	public Integer key() {
		return this.code;
	}
}
