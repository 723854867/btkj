package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.EmployeeForm;

public class EmployeeTips implements Serializable {

	private static final long serialVersionUID = -3946965045838944053L;

	private int id;						//邀请码
	private int uid;
	private int tid;
	private String name;
	private int regionId;
	private String regionName;
	private int parentId;				//邀请人id
	private String parentName;			//邀请人姓名
	private String tname;
	private String mobile;				//电话（账号）
	private String identity;
	private int created;				//注册时间
	
	public EmployeeTips() {}
	
	public EmployeeTips(EmployeeForm form, Region region) {
		this.id = form.getEmployee().getId();
		this.tid = form.getEmployee().getTid();
		this.uid  = form.getEmployee().getUid();
		this.name = form.getEmployee().getName();
		this.tname = form.getTenant().getName();
		this.regionId = region.getId();
		this.regionName = region.getName();
	}
	
	public EmployeeTips(Employee employee, User user) {
		this.id = employee.getId();
		this.tid = employee.getTid();
		this.uid  = employee.getUid();
		this.parentId = employee.getParentId();
		this.name = employee.getName();
		this.mobile = user.getMobile();
		this.identity = employee.getIdentity();
		this.created = employee.getCreated();
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
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}


	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}
}
