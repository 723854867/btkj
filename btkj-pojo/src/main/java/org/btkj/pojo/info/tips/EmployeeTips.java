package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;

public class EmployeeTips implements Serializable {

	private static final long serialVersionUID = -3946965045838944053L;

	private int id;
	private int uid;
	private int tid;
	private String name;
	private int regionId;
	private String regionName;
	
	public EmployeeTips() {}
	
	public EmployeeTips(Tenant tenant, Employee employee, User user) {
		this.id = employee.getId();
		this.tid = employee.getTid();
		this.uid  = user.getUid();
		this.name = user.getName();
		this.regionId = tenant.getRegion();
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
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}
