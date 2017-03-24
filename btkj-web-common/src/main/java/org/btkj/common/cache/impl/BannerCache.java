package org.btkj.common.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.AbstractCache;

public class BannerCache extends AbstractCache<Integer, Banner> {
	
	private TenantService tenantService;
	private Map<Integer, List<Banner>> tenantMap;

	public BannerCache(TenantService tenantService) {
		super(BtkjTables.BANNER.name());
		this.tenantService = tenantService;
	}

	@Override
	public void load() throws Exception {
		cache = tenantService.getBanners();
		Map<Integer, List<Banner>> map = new HashMap<Integer, List<Banner>>();
		for (Banner banner : cache.values()) {
			int tid = banner.getTid();
			List<Banner> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<Banner>();
				map.put(tid, list);
			}
			list.add(banner);
		}
		tenantMap = map;
	}
	
	public List<Banner> getTenantBanners(int tid) { 
		return tenantMap.get(tid);
	}
}
