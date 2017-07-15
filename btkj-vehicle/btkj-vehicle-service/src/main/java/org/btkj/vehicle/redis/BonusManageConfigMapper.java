package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.vehicle.mybatis.dao.BonusManageConfigDao;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class BonusManageConfigMapper extends RedisDBAdapter<String, BonusManageConfig, BonusManageConfigDao>{
	
	private final String TENANT_SET							= "set:bonus_manage_config:tenant:{0}";
	private final String TENANT_CONTROLLER					= "bonus_manage_config:controller:{0}";

	public BonusManageConfigMapper() {
		super(new ByteProtostuffSerializer<BonusManageConfig>(), "hash:db:bonus_manage_config");
	}
	
	public List<BonusManageConfig> getByTid(int tid) {
		Map<String, BonusManageConfig> map = _checkLoad(tid);
		if (null != map)
			return new ArrayList<BonusManageConfig>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _tenantSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<BonusManageConfig> l = new ArrayList<BonusManageConfig>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<String, BonusManageConfig> _checkLoad(int tid) {
		if (!checkLoad(_tenantControllerField(tid)))
			return null;
		Map<String, BonusManageConfig> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(BonusManageConfig entity) {
		redis.hmsset(redisKey, entity, serializer, _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void remove(BonusManageConfig entity) {
		redis.hmsdel(redisKey, entity.key(), _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void flush(Map<String, BonusManageConfig> entities) {
		Map<Integer, List<BonusManageConfig>> map = new HashMap<Integer, List<BonusManageConfig>>();
		for (BonusManageConfig temp : entities.values()) {
			int tid = temp.getTid();
			List<BonusManageConfig> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<BonusManageConfig>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<BonusManageConfig>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantSetKey(entry.getKey()));
	}
	
	private String _tenantSetKey(int tid) {
		return MessageFormat.format(TENANT_SET, String.valueOf(tid));
	}
	
	private String _tenantControllerField(int tid) {
		return MessageFormat.format(TENANT_CONTROLLER, String.valueOf(tid));
	}
}
