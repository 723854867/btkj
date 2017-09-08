package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;

public class EmployeeHolder implements Serializable {

	private static final long serialVersionUID = -1667633673051579348L;

	private AppPO app;
	private UserPO user;
	private TenantPO tenant;
	private EmployeePO employee;
	
	public EmployeeHolder() {}

	public EmployeeHolder(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee) {
		this.app = app;
		this.user = user;
		this.tenant = tenant;
		this.employee = employee;
	}

	public AppPO getApp() {
		return app;
	}

	public void setApp(AppPO app) {
		this.app = app;
	}

	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	public TenantPO getTenant() {
		return tenant;
	}

	public void setTenant(TenantPO tenant) {
		this.tenant = tenant;
	}

	public EmployeePO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeePO employee) {
		this.employee = employee;
	}
}
