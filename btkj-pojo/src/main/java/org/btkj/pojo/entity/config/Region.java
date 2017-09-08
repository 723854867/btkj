package org.btkj.pojo.entity.config;

import org.rapid.util.common.model.UniqueModel;

public class Region implements UniqueModel<Integer> {

	private static final long serialVersionUID = -1329367156319409927L;

	private int id;
	private int parentId;
	private int level;
	private String name;
	private String shortName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
