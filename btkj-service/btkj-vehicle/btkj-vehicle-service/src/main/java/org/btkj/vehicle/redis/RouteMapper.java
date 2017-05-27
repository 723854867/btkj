package org.btkj.vehicle.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Tenant;
import org.btkj.vehicle.mybatis.Tables;
import org.btkj.vehicle.mybatis.dao.RouteDao;
import org.btkj.vehicle.mybatis.entity.Route;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.Consts;

public class RouteMapper extends RedisProtostuffDBMapper<String, Route, RouteDao> {
	
	private String LIST							= "set:route:list:{0}";	
	private String LIST_CONTROLLER				= "route：controller:{0}";
	
	public RouteMapper() {
		super(Tables.ROUTE, "hash:db:route");
	}
	
	public Route getByTidAndInsurerId(int tid, int insurerId) {
		return getByKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
	}
	
	public List<Route> routes(Tenant tenant) {
		List<Route> routes = null;
		List<byte[]> list = redis.protostuffCacheListLoadWithData(BtkjConsts.CACHE_CONTROLLER, _listKey(tenant.getTid()), redisKey, _listController(tenant.getTid()));
		if (null != list) {
			routes = new ArrayList<Route>();
			for (byte[] buffer : list) 
				routes.add(deserial(buffer));
		} else {
			routes = dao.selectByTid(tenant.getTid());
			redis.protostuffCacheListFlush(BtkjConsts.CACHE_CONTROLLER, redisKey, _listKey(tenant.getTid()), _listController(tenant.getTid()), routes);
		}
		return routes;
	}
	
	@Override
	public void flush(Route entity) {
		redis.protostuffCacheFlush(redisKey, entity, _listKey(entity.getTid()));
	}
	
	public String _listKey(int tid) { 
		return MessageFormat.format(LIST, String.valueOf(tid));
	}
	
	private String _listController(int tid) {
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(tid));
	}
}
