package org.btkj.master.persistence.domain;

import org.rapid.data.storage.db.Entity;

public class Administrator implements Entity<Integer> {

	private static final long serialVersionUID = 517149369824450029L;
	
	private int id;
	private String pwd;
	private String name;
	private int created;
	private int updated;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
		return this.id;
	}
}
