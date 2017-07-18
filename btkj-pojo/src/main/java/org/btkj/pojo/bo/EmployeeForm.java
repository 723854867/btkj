package org.btkj.pojo.bo;

import java.io.Serializable;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;

public class EmployeeForm implements Serializable {

	private static final long serialVersionUID = 7056937855170092646L;

	private AppPO app;
	private UserPO user;
	private TenantPO tenant;
	private EmployeePO employee;
	
	public EmployeeForm() {}
	
	public EmployeeForm(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee) {
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
