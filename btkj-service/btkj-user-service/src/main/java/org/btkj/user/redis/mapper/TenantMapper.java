package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.dao.TenantDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class TenantMapper extends O2OMapper<Integer, Tenant, byte[], TenantDao> {

	public TenantMapper() {
		super(BtkjTables.TENANT, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tenantDataKey()));
	}
}
