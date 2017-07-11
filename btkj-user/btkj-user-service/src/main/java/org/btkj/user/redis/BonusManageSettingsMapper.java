package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.BtkjConsts;
import org.btkj.user.mybatis.dao.BonusManageSettingsDao;
import org.btkj.user.pojo.entity.BonusManageSettings;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtils;
import org.rapid.util.lang.StringUtils;

public class BonusManageSettingsMapper extends RedisDBAdapter<String, BonusManageSettings, BonusManageSettingsDao>{
	
	private final String CONTROLLER							= "bonus_manage_settings：controller:{0}";
	private final String TENANT_BASED_SET					= "set:bonus_manage_settings:{0}";

	public BonusManageSettingsMapper() {
		super(new ByteProtostuffSerializer<BonusManageSettings>(), "hash:db:bonus_manage_settings");
	}
	
	public List<BonusManageSettings> getByTenant(int tid) {
		_checkLoad(tid);
		List<byte[]> list = redis.hmsget(redisKey, _tenantBaseSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<BonusManageSettings> l = new ArrayList<BonusManageSettings>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private void _checkLoad(int tid) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _controkkerKey(tid), StringUtils.EMPTY))
			return;
		List<BonusManageSettings> list = dao.getByTid(tid);
		if (CollectionUtils.isEmpty(list))
			return;
		flush(list);
	}
	
	@Override
	public void flush(BonusManageSettings model) {
		redis.hmsset(redisKey, model, serializer, _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void remove(BonusManageSettings model) {
		redis.hmsdel(redisKey, model.key(), _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void flush(Collection<BonusManageSettings> models) {
		Map<Integer, List<BonusManageSettings>> map = new HashMap<Integer, List<BonusManageSettings>>();
		for (BonusManageSettings temp : models) {
			int tid = temp.getTid();
			List<BonusManageSettings> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<BonusManageSettings>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		
		for (Entry<Integer, List<BonusManageSettings>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantBaseSetKey(entry.getKey()));
	}
	
	private String _controkkerKey(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
	
	private String _tenantBaseSetKey(int tid) {
		return MessageFormat.format(TENANT_BASED_SET, String.valueOf(tid));
	}
}
