package org.btkj.user.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.App;
import org.btkj.user.mybatis.provider.AppSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface AppDao extends DBMapper<Integer, App> {
	
	@Override
	@InsertProvider(type = AppSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(App entity);
	
	@MapKey("id")
	@SelectProvider(type = AppSQLProvider.class, method = "getAll")
	Map<Integer, App> getAll();
	
	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "getByKey")
	App getByKey(Integer key);
	
	@SelectProvider(type = AppSQLProvider.class, method = "getByKeyForUpdate")
	App getByKeyForUpdate(Integer key);
	
	@Override
	@UpdateProvider(type = AppSQLProvider.class, method = "update")
	void update(App entity);
}
