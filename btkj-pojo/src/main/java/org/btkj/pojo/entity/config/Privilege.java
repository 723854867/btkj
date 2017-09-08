package org.btkj.pojo.entity.config;

import org.rapid.util.common.model.UniqueModel;

public class Privilege implements UniqueModel<String> {

	private static final long serialVersionUID = 5983230951829321938L;

	private String id;
	private int tarId;
	private int type;
	private int modularId;
	private int created;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getTarId() {
		return tarId;
	}

	public void setTarId(int tarId) {
		this.tarId = tarId;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public int getModularId() {
		return modularId;
	}

	public void setModularId(int modularId) {
		this.modularId = modularId;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String key() {
		return this.id;
	}
}
