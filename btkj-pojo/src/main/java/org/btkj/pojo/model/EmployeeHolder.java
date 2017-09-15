package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;

public class EmployeeHolder implements Serializable {

	private static final long serialVersionUID = -1667633673051579348L;

	private App app;
	private User user;
	private Tenant tenant;
	private Employee employee;
	
	public EmployeeHolder() {}

	public EmployeeHolder(App app, User user, Tenant tenant, Employee employee) {
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
