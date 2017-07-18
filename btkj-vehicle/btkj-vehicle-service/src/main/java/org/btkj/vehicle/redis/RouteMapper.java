package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.vehicle.mybatis.dao.RouteDao;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class RouteMapper extends RedisDBAdapter<String, Route, RouteDao> {
	
	private final String TENANT_SET							= "set:route:tenant:{0}";
	private final String CONTROLLER							= "route:controller:{0}";
	
	public RouteMapper() {
		super(new ByteProtostuffSerializer<Route>(), "hash:db:route");
	}
	
	public List<Route> getByTid(int tid) {
		Map<String, Route> map = _checkLoad(tid);
		if (null != map)
			return new ArrayList<Route>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _tenantSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<Route> l = new ArrayList<Route>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<String, Route> _checkLoad(int tid) {
		if (!checkLoad(_controllerField(tid)))
			return null;
		Map<String, Route> map = dao.getByTid(tid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(Route entity) {
		redis.hmsset(redisKey, entity, serializer, _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void remove(Route entity) {
		redis.hmsdel(redisKey, entity.key(), _tenantSetKey(entity.getTid()));
	}
	
	@Override
	public void flush(Map<String, Route> entities) {
		Map<Integer, List<Route>> map = new HashMap<Integer, List<Route>>();
		for (Route temp : entities.values()) {
			int tid = temp.getTid();
			List<Route> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<Route>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<Route>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantSetKey(entry.getKey()));
	}
	
	private String _tenantSetKey(int tid) {
		return MessageFormat.format(TENANT_SET, String.valueOf(tid));
	}
	
	private String _controllerField(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
}
