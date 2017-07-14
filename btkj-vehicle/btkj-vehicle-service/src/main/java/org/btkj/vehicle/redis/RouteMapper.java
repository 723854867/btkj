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
import org.btkj.vehicle.mybatis.dao.RouteDao;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

public class RouteMapper extends RedisDBAdapter<String, Route, RouteDao> {
	
	private final String CONTROLLER							= "route:controller:{0}";
	private final String TENANT_BASED_SET					= "set:route:{0}";
	
	public RouteMapper() {
		super(new ByteProtostuffSerializer<Route>(), "hash:db:route");
	}
	
	public List<Route> getByTid(int tid) {
		_checkLoad(tid);
		List<byte[]> list = redis.hmsget(redisKey, _tenantBaseSetKey(tid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<Route> l = new ArrayList<Route>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private void _checkLoad(int tid) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _controkkerKey(tid), StringUtil.EMPTY))
			return;
		List<Route> list = dao.getByTid(tid);
		if (CollectionUtil.isEmpty(list))
			return;
		flush(list);
	}
	
	@Override
	public void flush(Route model) {
		redis.hmsset(redisKey, model, serializer, _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void remove(Route model) {
		redis.hmsdel(redisKey, model.key(), _tenantBaseSetKey(model.getTid()));
	}
	
	@Override
	public void flush(Collection<Route> models) {
		Map<Integer, List<Route>> map = new HashMap<Integer, List<Route>>();
		for (Route temp : models) {
			int tid = temp.getTid();
			List<Route> list = map.get(tid);
			if (null == list) {
				list = new ArrayList<Route>();
				map.put(tid, list);
			}
			list.add(temp);
		}
		
		for (Entry<Integer, List<Route>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _tenantBaseSetKey(entry.getKey()));
	}
	
	private String _controkkerKey(int tid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(tid));
	}
	
	private String _tenantBaseSetKey(int tid) {
		return MessageFormat.format(TENANT_BASED_SET, String.valueOf(tid));
	}
	
}
