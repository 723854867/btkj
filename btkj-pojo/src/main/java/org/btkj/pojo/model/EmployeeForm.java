package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;

public class EmployeeForm implements Serializable {

	private static final long serialVersionUID = 7056937855170092646L;

	private App app;
	private User user;
	private Tenant tenant;
	private Employee employee;
	
	public EmployeeForm() {}
	
	public EmployeeForm(App app, User user, Tenant tenant, Employee employee) {
		this.app = app;
		this.user = user;
		this.tenant = tenant;
		this.employee = employee;
	}
	
	public App getApp() {
		return app;
	}
	
	public void setApp(App app) {
		this.app = app;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Tenant getTenant() {
		return tenant;
	}
	
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
