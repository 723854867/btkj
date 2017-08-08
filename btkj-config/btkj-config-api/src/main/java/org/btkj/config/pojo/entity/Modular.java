package org.btkj.config.pojo.entity;

import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.math.tree.mptt.MPTTNode;

public class Modular extends MPTTNode<Integer> implements UniqueModel<Integer> {

	private static final long serialVersionUID = 2316944779358104723L;

	private String name;
	private int type;
	private int created;
	private int updated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
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
