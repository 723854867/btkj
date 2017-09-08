package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.vehicle.BonusScaleConfig;
import org.btkj.vehicle.mybatis.dao.BonusScaleConfigDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class BonusScaleConfigMapper extends RedisDBAdapter<Integer, BonusScaleConfig, BonusScaleConfigDao> {
	
	private final String TENANT_SET							= "set:bonus_scale_config:tenant:{0}";
	private final String TENANT_CONTROLLER					= "bonus_scale_config:controller:{0}";

	public BonusScaleConfigMapper() {
		super(new ByteProtostuffSerializer<BonusScaleConfig>(), "hash:db:bonus_scale_config");
	}
	
	public List<BonusScaleConfig> getByTid(int tid) {
		Map<Integer, BonusScaleConfig> map = _checkLoad(tid);
		if (null != map)
			return new ArrayList<BonusScaleConfig>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _tenantSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<BonusScaleConfig> l = new ArrayList<BonusScaleConfig>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<Integer, BonusScaleConfig> _checkLoad(int tid) {
		if (!checkLoad(_tenantControllerField(tid)))
			return null;
		Map<Integer, BonusScaleConfig> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(BonusScaleConfig entity) {
		redis.hmsset(redisKey, entity, serializer, _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void remove(BonusScaleConfig entity) {
		redis.hmsdel(redisKey, entity.key(), _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void flush(Map<Integer, BonusScaleConfig> entities) {
		Map<Integer, List<BonusScaleConfig>> map = new HashMap<Integer, List<BonusScaleConfig>>();
		for (BonusScaleConfig temp : entities.values()) {
			int tid = temp.getTid();
			List<BonusScaleConfig> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<BonusScaleConfig>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<BonusScaleConfig>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantSetKey(entry.getKey()));
	}
	
	private String _tenantSetKey(int tid) {
		return MessageFormat.format(TENANT_SET, String.valueOf(tid));
	}
	
	private String _tenantControllerField(int tid) {
		return MessageFormat.format(TENANT_CONTROLLER, String.valueOf(tid));
	}
}
