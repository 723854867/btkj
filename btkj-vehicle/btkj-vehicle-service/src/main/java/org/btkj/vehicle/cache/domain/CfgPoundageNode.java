package org.btkj.vehicle.cache.domain;

import org.btkj.vehicle.pojo.enums.PoundageNodeType;
import org.rapid.util.common.model.UniqueModel;

public class CfgPoundageNode implements UniqueModel<Integer> {

	private static final long serialVersionUID = 3071455390317033127L;

	private int id;
	private String name;
	private PoundageNodeType type;
	
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
	
	public PoundageNodeType getType() {
		return type;
	}
	
	public void setType(PoundageNodeType type) {
		this.type = type;
	}
	
	@Override
	public Integer key() {
		return this.id;
	}
}
