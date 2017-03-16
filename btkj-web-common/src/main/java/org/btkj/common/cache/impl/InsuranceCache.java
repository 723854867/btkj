package org.btkj.common.cache.impl;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Insurance;
import org.rapid.util.common.cache.AbstractCache;

public class InsuranceCache extends AbstractCache<Integer, Insurance> {
	
	private ConfigService configService;

	public InsuranceCache(ConfigService configService) {
		super(BtkjTables.INSURANCE.name());
		this.configService = configService;
	}

	@Override
	public void load() throws Exception {
		cache = configService.getInsurances();
	}
}
