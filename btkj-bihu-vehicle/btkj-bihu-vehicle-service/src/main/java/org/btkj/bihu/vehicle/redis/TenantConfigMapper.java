package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.dao.TenantConfigDao;
import org.btkj.bihu.vehicle.mybatis.entity.TenantConfig;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class TenantConfigMapper extends RedisDBAdapter<Integer, TenantConfig, TenantConfigDao> {

	public TenantConfigMapper() {
		super(new ByteProtostuffSerializer<TenantConfig>(), "hash:db:tenant_config");
	}
}
