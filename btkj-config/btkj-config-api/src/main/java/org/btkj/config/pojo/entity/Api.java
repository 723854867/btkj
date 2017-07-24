package org.btkj.config.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Api implements UniqueModel<String> {

	private static final long serialVersionUID = 2316944779358104723L;

	private String key;
	private String name;
	private String groupMod;
	private int created;
	private int updated;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupMod() {
		return groupMod;
	}

	public void setGroupMod(String groupMod) {
		this.groupMod = groupMod;
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
		return this.key;
	}
}
