package org.btkj.pojo;

import org.rapid.data.storage.db.Entity;

public class Region implements Entity<Integer> {

	private static final long serialVersionUID = -1329367156319409927L;

	protected int code;
	protected String name;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer key() {
		return this.code;
	}
}
