package org.btkj.manager.web.cache.impl;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionDistrict;
import org.rapid.util.common.cache.AbstractCache;

public class RegionDistrictCache extends AbstractCache<Integer, RegionDistrict> {
	
	private ConfigService configService;

	public RegionDistrictCache(ConfigService configService) {
		super(BtkjTables.REGION_DISTRICT.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getRegionDistricts();
	}
}
