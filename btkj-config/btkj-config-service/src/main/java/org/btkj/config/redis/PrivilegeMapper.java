package org.btkj.config.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.config.mybatis.dao.PrivilegeDao;
import org.btkj.config.pojo.entity.Privilege;
import org.btkj.pojo.enums.ModularType;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.key.Pair;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class PrivilegeMapper extends RedisDBAdapter<String, Privilege, PrivilegeDao> {
	
	private final String SET								= "set:privilege:{0}:{1}";
	private final String CONTROLLER							= "controller:privilege:{0}:{1}";

	public PrivilegeMapper() {
		super(new ByteProtostuffSerializer<Privilege>(), "hash:db:privilege");
	}
	
	public Map<String, Privilege> privileges(ModularType type, int tarId) { 
		Map<String, Privilege> map = _checkLoad(type, tarId);
		if (null != map)
			return map;
		List<byte[]> list = redis.hmsget(redisKey, _setKey(type.mark(), tarId));
		if (null == list)
			return Collections.EMPTY_MAP;
		map = new HashMap<String, Privilege>();
		for (byte[] buffer : list) {
			Privilege temp = serializer.antiConvet(buffer);
			map.put(temp.getId(), temp);
		}
		return map;
	}
	
	public void privilegesClear(ModularType type, int tarId) { 
		redis.hmsdrop(redisKey, _setKey(type.mark(), tarId));
	}
	
	private Map<String, Privilege> _checkLoad(ModularType type, int tarId) {
		if (!checkLoad(_controllerField(type, tarId)))
			return null;
		Map<String, Privilege> map = dao.getByTypeAndTarId(type.mark(), tarId);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(Privilege entity) {
		redis.hmsset(redisKey, entity, serializer, _setKey(entity.getType(), entity.getTarId()));
	}
	
	@Override
	public void flush(Map<String, Privilege> entities) {
		Map<Pair<Integer, Integer>, List<Privilege>> map = new HashMap<Pair<Integer, Integer>, List<Privilege>>();
		for (Privilege temp : entities.values()) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>(temp.getType(), temp.getTarId());
			List<Privilege> list = map.get(key);
			if (null == list) {
				list = new ArrayList<Privilege>();
				map.put(key, list);
			}
			list.add(temp);
		}
		for (Entry<Pair<Integer, Integer>, List<Privilege>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _setKey(entry.getKey().getFirst(), entry.getKey().getSecond()));
	}
	
	private String _setKey(int tarType, int tarId) {
		return MessageFormat.format(SET, String.valueOf(tarType), String.valueOf(tarId));
	}
	
	private String _controllerField(ModularType type, int tarId) {
		return MessageFormat.format(CONTROLLER, String.valueOf(type.mark()), String.valueOf(tarId));
	}
}
