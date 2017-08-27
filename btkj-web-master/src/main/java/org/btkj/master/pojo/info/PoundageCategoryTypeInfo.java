package org.btkj.master.pojo.info;

import java.io.Serializable;

public class PoundageCategoryTypeInfo implements Serializable {

	private static final long serialVersionUID = -7261522072550619333L;

	private int id;
	private String name;
	
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
