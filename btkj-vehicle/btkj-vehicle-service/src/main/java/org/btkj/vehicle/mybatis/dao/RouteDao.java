package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.vehicle.mybatis.provider.RouteSQLProvider;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.data.storage.mapper.DBMapper;

public interface RouteDao extends DBMapper<String, Route> {
	
	@Override
	@InsertProvider(type = RouteSQLProvider.class, method = "insert")
	void insert(Route model);

	@Override
	@SelectProvider(type = RouteSQLProvider.class, method = "getByKey")
	Route getByKey(String key);
	
	@MapKey("key")
	@SelectProvider(type = RouteSQLProvider.class, method = "getByTid")
	Map<String, Route> getByTid(int tid);
	
	@Override
	@UpdateProvider(type = RouteSQLProvider.class, method = "update")
	void update(Route model);
	
	@DeleteProvider(type = RouteSQLProvider.class, method = "delete")
	void delete(String key);
}
