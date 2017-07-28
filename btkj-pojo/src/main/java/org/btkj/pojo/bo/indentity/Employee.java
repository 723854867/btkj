package org.btkj.pojo.bo.indentity;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;

public class Employee extends Identity {

	private static final long serialVersionUID = 2488943231324320042L;

	private AppPO app;
	private UserPO user;
	private TenantPO tenant;
	private EmployeePO entity;
	
	public Employee() {}
	
	public Employee(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee) {
		this.app = app;
		this.user = user;
		this.tenant = tenant;
		this.entity = employee;
	}
	
	public int getId() {
		return entity.getId();
	}
	
	public int getAppId() {
		return null == app ? 0 : app.getId();
	}
	
	public int getUid() {
		return user.getUid();
	}
	
	public int getTid() {
		return tenant.getTid();
	}
	
	public int getRegion() {
		return tenant.getRegion();
	}
	
	public int getLeft() {
		return entity.getLeft();
	}

	public int getRight() {
		return entity.getRight();
	}
	
	public int getLevel() {
		return entity.getLevel();
	}
	
	public String getName() {
		return user.getName();
	}
	
	public String getMobile() {
		return user.getMobile();
	}
	
	public int getTeamDepth() {
		return tenant.getTeamDepth();
	}
	
	public String tname() {
		return tenant.getName();
	}
	
	public String jianJieId() {
		return tenant.getJianJieId();
	}
	
	public int jianJieFetchTime() {
		return tenant.getJianJieFetchTime();
	}
	
	public AppPO getApp() {
		return app;
	}
	
	public UserPO getUser() {
		return user;
	}
	
	public TenantPO getTenant() {
		return tenant;
	}
	
	public void setTenant(TenantPO tenant) {
		this.tenant = tenant;
	}
	
	public EmployeePO getEntity() {
		return entity;
	}
	
	public void setEntity(EmployeePO entity) {
		this.entity = entity;
	}
}
