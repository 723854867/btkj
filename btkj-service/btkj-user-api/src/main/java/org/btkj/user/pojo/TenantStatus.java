package org.btkj.user.pojo;

import org.btkj.user.pojo.entity.Tenant;

public enum TenantStatus {

	ISOLATE(1);
	
	private int mod;
	
	private TenantStatus(int mod) {
		this.mod = mod;
	}
	
	public int mod() {
		return mod;
	}
	
	public static final boolean isIsolate(Tenant tenant) {
		return _hasStatus(tenant, TenantStatus.ISOLATE);
	}
	
	private static final boolean _hasStatus(Tenant tenant, TenantStatus status) {
		int statusMod = tenant.getStatsMod();
		return (statusMod & status.mod) == status.mod;
	}
}
