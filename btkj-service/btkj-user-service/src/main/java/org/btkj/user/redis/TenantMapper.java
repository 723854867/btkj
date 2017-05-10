package org.btkj.user.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.dao.TenantDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class TenantMapper extends RedisProtostuffDBMapper<Integer, Tenant, TenantDao> {
	
	protected TenantMapper() {
		super(BtkjTables.TENANT, "hash:db:tenant");
	}
	
	public int countByAppId(int appId) {
		return dao.countByAppId(appId);
	}
}
