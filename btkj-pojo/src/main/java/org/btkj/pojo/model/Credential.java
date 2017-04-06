package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;

/**
 * 身份识别类
 * 
 * @author ahab
 */
public class Credential implements Serializable {

	private static final long serialVersionUID = 549957907464373456L;

	private App app;
	private User user;
	private Tenant tenant;
	private Employee employee;				// 前提是有 Tenant
	private ClientType clientType;
	
	public Credential() {}
	
	public Credential(ClientType ct, App app) {
		this(ct, app, null, null, null);
	}
	
	public Credential(ClientType ct, App app, Tenant tenant) {
		this(ct, app, tenant, null, null);
	}
	
	public Credential(ClientType ct, App app, Tenant tenant, User user, Employee employee) {
		this.app = app;
		this.user = user;
		this.tenant = tenant;
		this.clientType = ct;
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
	
	public ClientType getClientType() {
		return clientType;
	}
	
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
}
