package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.persistence.Tables;
import org.btkj.bihu.vehicle.persistence.dao.TenantConfigDao;
import org.btkj.bihu.vehicle.persistence.entity.TenantConfig;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class TenantConfigMapper extends RedisProtostuffDBMapper<Integer, TenantConfig, TenantConfigDao> {

	public TenantConfigMapper() {
		super(Tables.TENANT_CONFIG, "hash:db:tenant_config");
	}
}
