package org.btkj.master.pojo.info;

import java.io.Serializable;

import org.btkj.master.service.Role;

public class MainPageInfo implements Serializable {

	private static final long serialVersionUID = 3859666037997238895L;

	private int id;
	private String name;
	
	public MainPageInfo() {}

	public MainPageInfo(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
