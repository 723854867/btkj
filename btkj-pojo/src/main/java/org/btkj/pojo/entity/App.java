package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;

public class App implements Entity<Integer> {

	private static final long serialVersionUID = 1327257995085623868L;

	private int id;
	private int mod;
	private String name;
	private int created;
	private int updated;
	
	public enum Mod {
		
		OFF_SHELF(1);			// 是否下架
		
		private int mark;
		
		private Mod(int mark) {
			this.mark = mark;
		}
		
		public int mark() {
			return mark;
		}
		
		public boolean hit(int mod) { 
			return (this.mark & mod) == this.mark;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
