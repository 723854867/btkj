package org.btkj.login.pojo.info;

import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.UserTips;

public class EmployeeInfo extends UserTips {

	private static final long serialVersionUID = -3946965045838944053L;

	private int id;						//邀请码
	private int tid;
	private int regionId;
	private String regionName;
	private int parentId;				//邀请人id
	private String parentName;			//邀请人姓名
	private String tname;
	private String mobile;				//电话（账号）
	private String identity;
	private int created;				//注册时间
	
	public EmployeeInfo() {}
	
	public EmployeeInfo(EmployeeTip employee, Region region) {
		super(employee);
		this.id = employee.getId();
		this.tid = employee.getTid();
		this.tname = employee.getTname();
		this.regionId = region.getId();
		this.regionName = region.getName();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
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
