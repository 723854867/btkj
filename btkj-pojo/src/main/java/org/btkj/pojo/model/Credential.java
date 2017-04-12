package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.CredentialSegment;

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
	private Client clientType;
	
	public Credential() {}
	
	public Credential(Client ct, App app, Tenant tenant) {
		this(ct, app, tenant, null, null);
	}
	
	public Credential(Client ct, App app, Tenant tenant, User user, Employee employee) {
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
	
	public Client getClientType() {
		return clientType;
	}
	
	public void setClientType(Client clientType) {
		this.clientType = clientType;
	}
	
	public int credentialMod() {
		int mod = 0;
		if (null != clientType)
			mod |= CredentialSegment.CLIENT_TYPE.mod();
		if (null != app)
			mod |= CredentialSegment.APP_ID.mod();
		if (null != tenant)
			mod |= CredentialSegment.TENANT_ID.mod();
		if (null != user)
			mod |= CredentialSegment.UID.mod();
		return mod;
	}
}
