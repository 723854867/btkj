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
import org.btkj.vehicle.mybatis.dao.BonusScaleConfigDao;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

public class BonusScaleConfigMapper extends RedisDBAdapter<Integer, BonusScaleConfig, BonusScaleConfigDao> {
	
	private final String CONTROLLER							= "bonus_scale_config:controller:{0}";
	private final String TENANT_BASED_SET					= "set:bonus_scale_config:{0}";

	public BonusScaleConfigMapper() {
		super(new ByteProtostuffSerializer<BonusScaleConfig>(), "hash:db:bonus_scale_config");
	}
	
	public List<BonusScaleConfig> getByTid(int tid) {
		_checkLoad(tid);
		List<byte[]> list = redis.hmsget(redisKey, _tenantBaseSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<BonusScaleConfig> l = new ArrayList<BonusScaleConfig>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private void _checkLoad(int tid) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _controkkerKey(tid), StringUtil.EMPTY))
			return;
		List<BonusScaleConfig> list = dao.getByTid(tid);
		if (CollectionUtil.isEmpty(list))
			return;
		flush(list);
	}
	
	@Override
	public void flush(BonusScaleConfig model) {
		redis.hmsset(redisKey, model, serializer, _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void remove(BonusScaleConfig model) {
		redis.hmsdel(redisKey, model.key(), _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void flush(Collection<BonusScaleConfig> models) {
		Map<Integer, List<BonusScaleConfig>> map = new HashMap<Integer, List<BonusScaleConfig>>();
		for (BonusScaleConfig temp : models) {
			int tid = temp.getTid();
			List<BonusScaleConfig> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<BonusScaleConfig>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		
		for (Entry<Integer, List<BonusScaleConfig>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantBaseSetKey(entry.getKey()));
	}
	
	private String _controkkerKey(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
	
	private String _tenantBaseSetKey(int tid) {
		return MessageFormat.format(TENANT_BASED_SET, String.valueOf(tid));
	}
}
