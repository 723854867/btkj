package org.btkj.user.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.dao.TenantDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;

public class TenantMapper extends ProtostuffDBMapper<Integer, Tenant, TenantDao> {
	
	private static final String TENANT_DATA				= "hash:db:tenant";

	protected TenantMapper() {
		super(BtkjTables.TENANT, TENANT_DATA);
	}
}
