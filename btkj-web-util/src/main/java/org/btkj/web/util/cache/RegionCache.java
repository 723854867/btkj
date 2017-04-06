package org.btkj.web.util.cache;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.rapid.util.common.cache.Cache;

public class RegionCache extends Cache<Integer, Region> {
	
	private ConfigService configService;

	public RegionCache(ConfigService configService) {
		super(BtkjTables.REGION.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getRegions();
	}
}
