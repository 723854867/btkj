package org.btkj.test.persistence.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.user.App;
import org.btkj.test.persistence.mapper.AppSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface AppDao extends DBMapper<Integer, App> {

	@Override
	@MapKey(value = "id")
	@SelectProvider(type = AppSQLProvider.class, method = "getAll")
	Map<Integer, App> getAll();
	
	@Override
	@MapKey(value = "id")
	@SelectProvider(type = AppSQLProvider.class, method = "getByKeys")
	Map<Integer, App> getByKeys(Collection<Integer> keys);
	
	@Override
	@InsertProvider(type = AppSQLProvider.class, method = "delete")
	void delete(Integer key);
}
