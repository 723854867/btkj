package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.EmployeeForm;

public class EmployeeTips implements Serializable {

	private static final long serialVersionUID = -3946965045838944053L;

	private int id;
	private int uid;
	private int tid;
	private String name;
	private int regionId;
	private String regionName;
	private String tname;
	private int app_id;
	private int parent_id;				//邀请人id
	private int invitation_code;		//邀请码
	private String parent_name;			//邀请人姓名
	private String mobile;				//电话（账号）
	private String identity;
	private int created;				//注册时间
	private int mod;					//状态模值 
	
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
		this.uid = user.getUid();
		this.name = employee.getName();
		this.app_id = user.getAppId();
		this.parent_id = employee.getParentId();
		this.mobile = user.getMobile();
		this.identity = employee.getIdentity();
		this.created = user.getCreated();
		this.mod = employee.getMod();
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

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getInvitation_code() {
		return invitation_code;
	}

	public void setInvitation_code(int invitation_code) {
		this.invitation_code = invitation_code;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
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

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}
	
	
}
