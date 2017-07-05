package org.btkj.bihu.vehicle.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.bihu.vehicle.mybatis.dao.TenantConfigDao;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class TenantConfigMapper extends RedisDBAdapter<Integer, TenantConfig, TenantConfigDao> {
	
	private String pagingZset							= "zset:bi_hu_tenant_config:{0}";
	private String cacheControllerField					= "lock:bi_hu_tenant_config";

	public TenantConfigMapper() {
		super(new ByteProtostuffSerializer<TenantConfig>(), "hash:db:tenant_config");
	}
	
	public Result<Pager<TenantConfig>> paging(int page, int pageSize) {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(pagingZset), 
				SerializeUtil.RedisUtil.encode(redisKey), 
				SerializeUtil.RedisUtil.encode(page), 
				SerializeUtil.RedisUtil.encode(pageSize),
				SerializeUtil.RedisUtil.encode(RedisConsts.OPTION_ZREVRANGE));
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<TenantConfig> configs = new ArrayList<TenantConfig>();
		for (byte[] data : list)
			configs.add(serializer.antiConvet(data));
		return Result.result(new Pager<TenantConfig>(total, configs));
	}
	
	@Override
	public void flush(Collection<TenantConfig> configs) {
		super.flush(configs);
		Map<String, Double> map = new HashMap<String, Double>();
		for (TenantConfig config : configs)
			map.put(String.valueOf(config.getKey()), Double.valueOf(config.getCreated()));
		redis.zadd(pagingZset, map);
	}
	
	@Override
	public void flush(TenantConfig entity) {
		super.flush(entity);
		redis.zadd(pagingZset, entity.getCreated(), String.valueOf(entity.getKey()));
	}
}
