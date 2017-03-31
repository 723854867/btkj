package org.btkj.manager.web.cache;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.web.cache.impl.RegionCityCache;
import org.btkj.manager.web.cache.impl.RegionDistrictCache;
import org.btkj.manager.web.cache.impl.RegionProvinceCache;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.Region;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.cache.AbstractCacheService;
import org.rapid.util.common.cache.Cache;
import org.rapid.util.common.region.RegionType;
import org.rapid.util.common.region.RegionUtil;
import org.springframework.stereotype.Service;

@Service
public class CacheService extends AbstractCacheService<Cache<?,?>> {

	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;
	
	@Override
	public void init() {
		addCache(new RegionCityCache(configService));
		addCache(new RegionDistrictCache(configService));
		addCache(new RegionProvinceCache(configService));
		super.init();
	}
	
	public Region getRegionByCode(int code) { 
		RegionType type = RegionUtil.type(code);
		switch (type) {
		case PROVINCE:
			return getById(BtkjTables.REGION_PROVINCE.name(), code);
		case CITY:
			return getById(BtkjTables.REGION_CITY.name(), code);
		case DISTRICT:
			return getById(BtkjTables.REGION_DISTRICT.name(), code);
		default:
			throw new IllegalArgumentException("Unsupported region type!");
		}
	}
}