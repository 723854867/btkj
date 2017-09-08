package org.btkj.pojo.info.user;

import java.io.Serializable;

import org.btkj.pojo.entity.user.AppPO;

public class AppInfo implements Serializable {

	private static final long serialVersionUID = 8437274110716771303L;

	private int id;
	private int mod;
	private int region;
	private String name;
	private String regionName;
	private int maxTenantsCount;
	private int maxArticlesCount;
	private int created;
	private int updated;
	
	public AppInfo() {}
	
	public AppInfo(AppPO po) {
		this.id = po.getId();
		this.mod = po.getMod();
		this.region = po.getRegion();
		this.name = po.getName();
		this.maxTenantsCount = po.getMaxTenantsCount();
		this.maxArticlesCount = po.getMaxArticlesCount();
		this.created = po.getCreated();
		this.updated = po.getUpdated();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
}
