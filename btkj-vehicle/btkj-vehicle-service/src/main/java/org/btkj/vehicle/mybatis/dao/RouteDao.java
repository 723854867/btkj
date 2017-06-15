package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.entity.Route;
import org.btkj.vehicle.mybatis.provider.RouteSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface RouteDao extends DBMapper<String, Route> {

	@Override
	@SelectProvider(type = RouteSQLProvider.class, method = "getByKey")
	Route getByKey(String key);
	
	@SelectProvider(type = RouteSQLProvider.class, method = "getByTid")
	List<Route> getByTid(int tid);
}
