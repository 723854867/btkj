package org.btkj.pojo.info.tips;

import java.io.Serializable;

public class EmployeeTips implements Serializable {

	private static final long serialVersionUID = -3946965045838944053L;

	private int id;
	private int uid;
	private int tid;
	private String name;
	private String region;
	
	public EmployeeTips() {}
	
	public EmployeeTips(int id, int uid, int tid, String name) {
		this.id = id;
		this.uid  = uid;
		this.tid = tid;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
}
