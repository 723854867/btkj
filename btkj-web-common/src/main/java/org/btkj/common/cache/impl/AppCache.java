package org.btkj.common.cache.impl;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.AbstractCache;

public class AppCache extends AbstractCache<Integer, App> {
	
	private TenantService tenantService;

	public AppCache(TenantService tenantService) {
		super(BtkjTables.APP.name());
		this.tenantService = tenantService;
	}

	@Override
	public void load() throws Exception {
		cache = tenantService.getApps();
	}
}
