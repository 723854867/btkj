package org.btkj.web.util.cache;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Insurer;
import org.rapid.util.common.cache.Cache;

public class InsuranceCache extends Cache<Integer, Insurer> {
	
	private ConfigService configService;

	public InsuranceCache(ConfigService configService) {
		super(BtkjTables.INSURER.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getInsurances();
	}
}
