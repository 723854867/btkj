package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Tenant;
import org.btkj.vehicle.mybatis.dao.RouteDao;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class RouteMapper extends RedisDBAdapter<String, Route, RouteDao> {
	
	private String LIST							= "set:route:list:{0}";	
	private String LIST_CONTROLLER				= "route：controller:{0}";
	
	public RouteMapper() {
		super(new ByteProtostuffSerializer<Route>(), "hash:db:route");
	}
	
	public List<Route> routes(Tenant tenant) {
		List<Route> routes = null;
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _listKey(tenant.getTid()), redisKey, _listController(tenant.getTid()));
		if (null != list) {
			routes = new ArrayList<Route>();
			for (byte[] buffer : list) 
				routes.add(serializer.antiConvet(buffer));
		} else {
			routes = dao.getByTid(tenant.getTid());
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _listKey(tenant.getTid()), _listController(tenant.getTid()), routes, serializer);
		}
		return routes;
	}
	
	@Override
	public void flush(Route entity) {
		redis.hsset(redisKey, entity.key(), serializer.convert(entity), _listKey(entity.getTid()));
	}
	
	public String _listKey(int tid) { 
		return MessageFormat.format(LIST, String.valueOf(tid));
	}
	
	private String _listController(int tid) {
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(tid));
	}
}
