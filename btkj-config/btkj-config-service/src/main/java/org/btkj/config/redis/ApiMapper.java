package org.btkj.config.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.config.mybatis.dao.ApiDao;
import org.btkj.config.pojo.entity.Api;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class ApiMapper extends RedisDBAdapter<Integer, Api, ApiDao> {
	
	private final String SET								= "set:api:{0}";
	private final String CONTROLLER							= "api:controller:{0}";

	public ApiMapper() {
		super(new ByteProtostuffSerializer<Api>(), "hash:db:api");
	}
	
	public Map<Integer, Api> apis(int modularId) {
		Map<Integer, Api> map = _checkLoad(modularId);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(modularId));
		if (null == list)
			return Collections.EMPTY_MAP;
		map = new HashMap<Integer, Api>();
		for (byte[] buffer : list) {
			Api api = serializer.antiConvet(buffer);
			map.put(api.getId(), api);
		}
		return map;
	}
	
	private Map<Integer, Api> _checkLoad(int modularId) {
		if (!checkLoad(_controllerField(modularId)))
			return null;
		Map<Integer, Api> map = dao.getByModularId(modularId);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(Api entity) {
		redis.hmsset(redisKey, entity, serializer, _setKey(entity.getModularId()));
	}
	
	@Override
	public void remove(Api entity) {
		redis.hmsdel(redisKey, entity.key(), _setKey(entity.getModularId()));
	}
	
	@Override
	public void flush(Map<Integer, Api> entities) {
		Map<Integer, List<Api>> map = new HashMap<Integer, List<Api>>();
		for (Api temp : entities.values()) {
			int modularId = temp.getModularId();
			List<Api> list = map.get(modularId);
			if (null == list) {
				list = new ArrayList<Api>();
				map.put(modularId, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<Api>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getKey()));
	}
	
	private String _setKey(int modularId) {
		return MessageFormat.format(SET, String.valueOf(modularId));
	}
	
	private String _controllerField(int modularId) {
		return MessageFormat.format(CONTROLLER, String.valueOf(modularId));
	}
}
