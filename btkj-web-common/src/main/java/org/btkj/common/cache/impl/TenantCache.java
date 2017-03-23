package org.btkj.common.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.AbstractCache;

public class TenantCache extends AbstractCache<Integer, Tenant> {
	
	private TenantService tenantService;
	private List<Tenant> baotuTenants;						// 保途租户
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
		List<Tenant> list = new ArrayList<Tenant>();
		for (Tenant tenant : temp.values()) {
			if (BtkjUtil.isBaoTuApp(tenant.getAppId()))
				list.add(tenant);
			else
				map.put(tenant.getAppId(), tenant);
		}
		baotuTenants = list;
		isolateTenants = map;
	}
	
	public Tenant getIsolateTenant(int appId) { 
		return isolateTenants.get(appId);
	}
}
