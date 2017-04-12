package org.btkj.web.util.cache;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.Cache;

public class AppCache extends Cache<Integer, App> {
	
	private TenantService tenantService;

	public AppCache(TenantService tenantService) {
		super(BtkjTables.APP.name());
		this.tenantService = tenantService;
	}
	
	/**
	 * 加载顺序必须大于 tenantCache
	 */
	@Override
	public int priority() {
		return 1;
	}

	@Override
	public void load() throws Exception {
		cache = tenantService.getApps();
	}
}
