package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;

public class InviterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -828584577019847673L;

	private App app;
	private Tenant tenant;
	private User user;
	
	public InviterModel() {}
	
	public InviterModel(App app, Tenant tenant, User user) {
		this.app = app;
		this.tenant = tenant;
		this.user = user;
	}

	public App getApp() {
		return app;
	}
	
	public void setApp(App app) {
		this.app = app;
	}
	
	public Tenant getTenant() {
		return tenant;
	}
	
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
