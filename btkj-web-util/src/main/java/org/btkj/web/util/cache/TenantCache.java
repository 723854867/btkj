package org.btkj.web.util.cache;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.Cache;

public class TenantCache extends Cache<Integer, Tenant> {
	
	private TenantService tenantService;
	private Map<Integer, Tenant> baotuTenants;				// 保途租户
	private Map<Integer, Tenant> isolateTenants;			// 独立 app 租户

	public TenantCache(TenantService tenantService) {
		super(BtkjTables.TENANT.name());
		this.tenantService = tenantService;
	}

	@Override
	public void load() throws Exception {
		cache = tenantService.getTenants();
		Map<Integer, Tenant> temp = this.cache;
		Map<Integer, Tenant> map = new HashMap<Integer, Tenant>();
		Map<Integer, Tenant> map1 = new HashMap<Integer, Tenant>();
		for (Tenant tenant : temp.values()) {
			if (BtkjUtil.isBaoTuApp(tenant.getAppId()))
				map1.put(tenant.getTid(), tenant);
			else
				map.put(tenant.getAppId(), tenant);
		}
		baotuTenants = map1;
		isolateTenants = map;
	}
	
	public Tenant getBaotuTenant(int tid) {
		return baotuTenants.get(tid);
	}
	
	public Tenant getIsolateTenant(int appId) { 
		return isolateTenants.get(appId);
	}
}
