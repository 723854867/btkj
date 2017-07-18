package org.btkj.pojo.bo;

import java.io.Serializable;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;

public class AppCreateModel implements Serializable {

	private static final long serialVersionUID = 2890492992297291046L;

	private AppPO app;
	private TenantPO tenant;
	private UserPO user;
	
	public AppCreateModel() {}
	
	public AppCreateModel(AppPO app, TenantPO tenant, UserPO user) {
		this.app = app;
		this.tenant = tenant;
		this.user = user;
	}
	
	public AppPO getApp() {
		return app;
	}
	
	public void setApp(AppPO app) {
		this.app = app;
	}
	
	public TenantPO getTenant() {
		return tenant;
	}
	
	public void setTenant(TenantPO tenant) {
		this.tenant = tenant;
	}
	
	public UserPO getUser() {
		return user;
	}
	
	public void setUser(UserPO user) {
		this.user = user;
	}
}
