package org.btkj.config.pojo.entity;

import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.math.tree.mptt.MPTTNode;

public class Modular extends MPTTNode<String> implements UniqueModel<String> {

	private static final long serialVersionUID = 2316944779358104723L;

	private String name;
	private String mod;
	private int created;
	private int updated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
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
		return this.id;
	}
}
