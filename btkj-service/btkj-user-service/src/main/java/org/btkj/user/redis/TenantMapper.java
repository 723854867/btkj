package org.btkj.user.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.dao.TenantDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class TenantMapper extends ProtostuffDBMapper<Integer, Tenant, TenantDao> {
	
	private static final String TENANT_DATA				= "hash:db:tenant";

	protected TenantMapper() {
		super(BtkjTables.TENANT, TENANT_DATA);
	}
	
	public List<Tenant> getTenants(Set<?> tids) { 
		byte[][] fields = new byte[tids.size()][];
		int index = 0;
		for (Object tid : tids)
			fields[index++] = SerializeUtil.RedisUtil.encode(tid);
		List<byte[]> datas = redis.hmget(SerializeUtil.RedisUtil.encode(TENANT_DATA), fields);
		List<Tenant> tenants = new ArrayList<Tenant>();
		for (byte[] data : datas)
			tenants.add(deserial(data));
		return tenants;
	}
}
