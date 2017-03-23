package org.btkj.user.redis.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.dao.TenantDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class TenantMapper extends O2OMapper<Integer, Tenant, byte[], TenantDao> {

	public TenantMapper() {
		super(BtkjTables.TENANT, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tenantDataKey()));
	}

	/**
	 * 设置好 appId 和 tenant 的映射
	 * 
	 */
	@Override
	protected void initHook(List<Tenant> entities) {
		Set<String> set = new HashSet<String>();
		Map<String, String> map = new HashMap<String, String>();
		for (Tenant tenant : entities) {
			if (BtkjUtil.isBaoTuApp(tenant.getAppId()))
				set.add(String.valueOf(tenant.getAppId()));
			else
				map.put(String.valueOf(tenant.getAppId()), String.valueOf(tenant.getTid()));
		}
		redis.delAndSadd(RedisKeyGenerator.btkjTenantKey(), set);
		redis.delAndHmset(RedisKeyGenerator.isolateAppTenantKey(), map);
	}
	
	public int getTidByAppId(int appId) { 
		return Integer.valueOf(redis.hget(RedisKeyGenerator.isolateAppTenantKey(), String.valueOf(appId)));
	}
}
