package org.btkj.user.redis;

import org.btkj.pojo.entity.Tenant;
import org.btkj.user.mybatis.dao.TenantDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class TenantMapper extends RedisDBAdapter<Integer, Tenant, TenantDao> {
	
	public TenantMapper() {
		super(new ByteProtostuffSerializer<Tenant>(), "hash:db:tenant");
	}
	
	public int countByAppId(int appId) {
		return dao.countByAppId(appId);
	}
}
