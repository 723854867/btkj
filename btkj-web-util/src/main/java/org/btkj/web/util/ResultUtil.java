package org.btkj.web.util;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.info.tips.BannerTips;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.web.util.cache.BannerCache;
import org.btkj.web.util.cache.RegionCache;
import org.btkj.web.util.cache.TenantCache;
import org.rapid.util.common.cache.CacheService;

public class ResultUtil {
	
	private static CacheService<?> cacheService;

	public static final void fillMainPageInfo(AppMainPageInfo pageInfo) {
		_fillMainTenant(pageInfo);
		_fillBtkjTenantList(pageInfo);
	}

	private static final void _fillMainTenant(AppMainPageInfo pageInfo) {
		MainTenantTips mainTenant = pageInfo.getTenant();
		Tenant tenant = cacheService.getById(BtkjTables.TENANT.name(), mainTenant.getTid());
		mainTenant.setName(tenant.getName());
		RegionCache rc = (RegionCache) cacheService.getCache(BtkjTables.REGION.name());
		Region region = rc.getById(tenant.getRegionId());
		mainTenant.setRegion(region.getName());
		BannerCache cache = (BannerCache) cacheService.getCache(BtkjTables.BANNER.name());
		List<Banner> banners = cache.getTenantBanners(mainTenant.getTid());
		if (null != banners) {
			List<BannerTips> tips = new ArrayList<BannerTips>();
			for (Banner banner : banners)
				tips.add(new BannerTips(banner));
			mainTenant.setBannerList(tips);
		}
	}

	private static final void _fillBtkjTenantList(AppMainPageInfo pageInfo) {
		TenantCache cache = (TenantCache) cacheService.getCache(BtkjTables.TENANT.name());
		_fillTenantTips(pageInfo.getOwnTenants(), cache);
		_fillTenantTips(pageInfo.getAuditTenants(), cache);
	}

	private static final void _fillTenantTips(List<TenantTips> list, TenantCache cache) {
		if (null != list && !list.isEmpty()) {
			for (TenantTips tips : list) {
				Tenant tenant = cache.getById(tips.getTid());
				tips.setName(tenant.getName());
			}
		}
	}
	
	public static void setCacheService(CacheService<?> cacheService) {
		ResultUtil.cacheService = cacheService;
	}
}
