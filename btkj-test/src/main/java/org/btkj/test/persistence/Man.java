package org.btkj.test.persistence;

import java.io.Serializable;

public class Man implements Serializable {

	private static final long serialVersionUID = 6426870914755745012L;

	private int id;
	private String name;
	private String _id;
	private String test_as;
	
	public String getTest_as() {
		return "sss";
	}
	
	public String get_id() {
		return "sdd";
	}
	
	public int getId() {
		return 100;
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
	
	public String getAgeTs() {
		return "10";
	}
}
