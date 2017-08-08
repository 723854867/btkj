package org.btkj.config.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Api implements UniqueModel<String> {

	private static final long serialVersionUID = 2316944779358104723L;

	private String pkg;
	private String name;
	private int modularId;
	private int created;
	private int updated;

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public String key() {
		return null;
	}
}
