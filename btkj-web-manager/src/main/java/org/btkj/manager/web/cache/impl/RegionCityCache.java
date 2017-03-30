package org.btkj.manager.web.cache.impl;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionCity;
import org.rapid.util.common.cache.AbstractCache;

public class RegionCityCache extends AbstractCache<Integer, RegionCity> {
	
	private ConfigService configService;

	public RegionCityCache(ConfigService configService) {
		super(BtkjTables.REGION_CITY.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getRegionCities();
	}
}
