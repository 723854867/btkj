package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.mybatis.dao.BonusManageConfigDao;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

public class BonusManageConfigMapper extends RedisDBAdapter<String, BonusManageConfig, BonusManageConfigDao>{
	
	private final String CONTROLLER							= "bonus_manage_config:controller:{0}";
	private final String TENANT_BASED_SET					= "set:bonus_manage_config:{0}";

	public BonusManageConfigMapper() {
		super(new ByteProtostuffSerializer<BonusManageConfig>(), "hash:db:bonus_manage_config");
	}
	
	public List<BonusManageConfig> getByTid(int tid) {
		_checkLoad(tid);
		List<byte[]> list = redis.hmsget(redisKey, _tenantBaseSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<BonusManageConfig> l = new ArrayList<BonusManageConfig>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private void _checkLoad(int tid) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _controkkerKey(tid), StringUtil.EMPTY))
			return;
		List<BonusManageConfig> list = dao.getByTid(tid);
		if (CollectionUtil.isEmpty(list))
			return;
		flush(list);
	}
	
	@Override
	public void flush(BonusManageConfig model) {
		redis.hmsset(redisKey, model, serializer, _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void remove(BonusManageConfig model) {
		redis.hmsdel(redisKey, model.key(), _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void flush(Collection<BonusManageConfig> models) {
		Map<Integer, List<BonusManageConfig>> map = new HashMap<Integer, List<BonusManageConfig>>();
		for (BonusManageConfig temp : models) {
			int tid = temp.getTid();
			List<BonusManageConfig> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<BonusManageConfig>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		
		for (Entry<Integer, List<BonusManageConfig>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantBaseSetKey(entry.getKey()));
	}
	
	private String _controkkerKey(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
	
	private String _tenantBaseSetKey(int tid) {
		return MessageFormat.format(TENANT_BASED_SET, String.valueOf(tid));
	}
}
