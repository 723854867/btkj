package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.entity.Route;
import org.btkj.vehicle.mybatis.provider.RouteSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface RouteDao extends Dao<String, Route> {

	@Override
	@SelectProvider(type = RouteSQLProvider.class, method = "selectByKey")
	Route selectByKey(String key);
	
	@SelectProvider(type = RouteSQLProvider.class, method = "selectByTid")
	List<Route> selectByTid(int tid);
}
