package org.btkj.pojo.po;

import org.rapid.util.common.model.UniqueModel;

public class AppPO implements UniqueModel<Integer> {

	private static final long serialVersionUID = 1327257995085623868L;

	private int id;
	private int region;
	private String name;
	private int maxTenantsCount;
	private int maxArticlesCount;
	private String modularMod;
	private int created;
	private int updated;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxTenantsCount() {
		return maxTenantsCount;
	}
	
	public void setMaxTenantsCount(int maxTenantsCount) {
		this.maxTenantsCount = maxTenantsCount;
	}
	
	public int getMaxArticlesCount() {
		return maxArticlesCount;
	}
	
	public void setMaxArticlesCount(int maxArticlesCount) {
		this.maxArticlesCount = maxArticlesCount;
	}
	
	public String getModularMod() {
		return modularMod;
	}
	
	public void setModularMod(String modularMod) {
		this.modularMod = modularMod;
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
		return id;
	}
}
