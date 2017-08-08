package org.btkj.config.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.pojo.enums.ModularType;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

/**
 * 该缓存仅仅缓存 name，mod 值，不做结构缓存，因为 left，right等值的修改是直接操作 db 的
 * 
 * @author ahab
 *
 */
public class ModularMapper extends RedisDBAdapter<Integer, Modular, ModularDao> {
	
	private final String SET							= "set:modular:{}";
	private final String CONTROLLER						= "modular:controller:{}";

	public ModularMapper() {
		super(new ByteProtostuffSerializer<Modular>(), "hash:db:modular");
	}
	
	public Map<Integer, Modular> modulars(ModularType type) {
		Map<Integer, Modular> map = _checkLoad(type);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(type.mark()));
		if (null == list)
			return Collections.EMPTY_MAP;
		map = new HashMap<Integer, Modular>();
		for (byte[] buffer : list) {
			Modular temp = serializer.antiConvet(buffer);
			map.put(temp.getId(), temp);
		}
		return map;
	}
	
	private Map<Integer, Modular> _checkLoad(ModularType type) {
		if (!checkLoad(_controllerField(type.mark())))
			return null;
		Map<Integer, Modular> map = dao.getByType(type.mark());
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(Modular entity) {
		redis.hmsset(redisKey, entity, serializer, _setKey(entity.getType()));
	}
	
	@Override
	public void flush(Map<Integer, Modular> entities) {
		Map<Integer, List<Modular>> map = new HashMap<Integer, List<Modular>>();
		for (Modular temp : entities.values()) {
			int type = temp.getType();
			List<Modular> list = map.get(type);
			if (null == list) {
				list = new ArrayList<Modular>();
				map.put(type, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<Modular>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getKey()));
	}
	
	public String _setKey(int type) { 
		return MessageFormat.format(SET, String.valueOf(type));
	}
	
	private String _controllerField(int type) {
		return MessageFormat.format(CONTROLLER, String.valueOf(type));
	}
}
