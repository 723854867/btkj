package org.btkj.common.cache.impl;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionProvince;
import org.rapid.util.common.cache.AbstractCache;

public class RegionProvinceCache extends AbstractCache<Integer, RegionProvince> {
	
	private ConfigService configService;

	public RegionProvinceCache(ConfigService configService) {
		super(BtkjTables.REGION_PROVINCE.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getRegionProvinces();
	}
}
