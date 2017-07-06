package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Employee;

public class EmployeePagingInfo implements Serializable {

	private static final long serialVersionUID = 391382868580464606L;

	private int id;
	private int uid;
	private String name;
	private String mobile;
	private int payType;
	private int parentId;
	private int parentUid;
	private String parentName;
	private String parentMobile;
	private int created;
	
	public EmployeePagingInfo() {}
	
	public EmployeePagingInfo(Employee employee) {
		this.id = employee.getId();
		this.uid = employee.getUid();
		this.payType = employee.getPayType();
		this.parentId = employee.getParentId();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public int getParentUid() {
		return parentUid;
	}
	
	public void setParentUid(int parentUid) {
		this.parentUid = parentUid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}
}
