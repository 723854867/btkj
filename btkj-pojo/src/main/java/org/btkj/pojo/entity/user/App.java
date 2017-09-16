package org.btkj.pojo.entity.user;

import org.rapid.util.common.model.UniqueModel;

public class App implements UniqueModel<Integer> {

	private static final long serialVersionUID = 1327257995085623868L;

	private int id;
	private int mod;
	private int region;
	private String name;
	private int maxTenantsCount;
	private int maxArticlesCount;
	private int created;
	private int updated;
	
	public enum Mod {
		SEAL(1);
		private int mark;
		private Mod(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public boolean satisfy(int cmod) {
			return (cmod & mark) == mark;
		}
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
	
	@Override
	public Integer key() {
		return id;
	}
}