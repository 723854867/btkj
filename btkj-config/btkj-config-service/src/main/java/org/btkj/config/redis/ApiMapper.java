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

public class ApiMapper extends RedisDBAdapter<String, Api, ApiDao> {
	
	private final String TENANT_SET							= "set:api:{0}";
	private final String CONTROLLER							= "api:controller:{0}";

	public ApiMapper() {
		super(new ByteProtostuffSerializer<Api>(), "hash:db:api");
	}
	
	public Map<String, Api> apis(String modularId) {
		Map<String, Api> map = _checkLoad(modularId);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(modularId));
		if (null == list)
			return Collections.EMPTY_MAP;
		map = new HashMap<String, Api>();
		for (byte[] buffer : list) {
			Api api = serializer.antiConvet(buffer);
			map.put(api.getModularId(), api);
		}
		return map;
	}
	
	private Map<String, Api> _checkLoad(String modularId) {
		if (!checkLoad(_controllerField(modularId)))
			return CollectionUtil.EMPTY_MAP;
		Map<String, Api> map = dao.getByModularId(modularId);
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
	public void flush(Map<String, Api> entities) {
		Map<String, List<Api>> map = new HashMap<String, List<Api>>();
		for (Api temp : entities.values()) {
			String modularId = temp.getModularId();
			List<Api> list = map.get(modularId);
			if (null == list) {
				list = new ArrayList<Api>();
				map.put(modularId, list);
			}
			list.add(temp);
		}
		for (Entry<String, List<Api>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getKey()));
	}
	
	private String _setKey(String modularId) {
		return MessageFormat.format(TENANT_SET, modularId);
	}
	
	private String _controllerField(String modularId) {
		return MessageFormat.format(CONTROLLER, modularId);
	}
}
