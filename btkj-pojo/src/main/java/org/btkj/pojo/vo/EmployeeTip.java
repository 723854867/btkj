package org.btkj.pojo.vo;

import java.io.Serializable;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;

/**
 * 雇员基本信息
 * 
 * @author ahab
 */
public class EmployeeTip implements Serializable {

	private static final long serialVersionUID = -273300203666288418L;

	private int id;
	private int uid;
	private int tid;
	private int appId;
	private int aregion;
	private int tregion;
	private String name;				// 用户名字
	private String aname;				// app名字
	private String tname;				// 商户名字
	private String mobile;				// 手机号
	private String avatar;
	private String identity;			// 身份证号
	
	public EmployeeTip() {}
	
	public EmployeeTip(EmployeePO employee, AppPO app, UserPO user, TenantPO tenant) {
		this.id = employee.getId();
		this.uid = employee.getUid();
		this.tid = employee.getTid();
		this.appId = employee.getAppId();
		this.aregion = app.getRegion();
		this.tregion = tenant.getRegion();
		this.name = user.getName();
		this.aname = app.getName();
		this.tname = tenant.getName();
		this.mobile = user.getMobile();
		this.avatar = user.getAvatar();
		this.identity = user.getIdentity();
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

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}
	
	public String getAname() {
		return aname;
	}
	
	public void setAname(String aname) {
		this.aname = aname;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
