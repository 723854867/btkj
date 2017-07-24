package org.btkj.config.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.ApiSQLProvider;
import org.btkj.config.pojo.entity.Api;
import org.rapid.data.storage.mapper.DBMapper;

public interface ApiDao extends DBMapper<String, Api> {

	@Override
	@InsertProvider(type = ApiSQLProvider.class, method = "insert")
	void insert(Api model);
	
	@Override
	@MapKey("key")
	@SelectProvider(type = ApiSQLProvider.class, method = "getAll")
	Map<String, Api> getAll();
	
	@Override
	@SelectProvider(type = ApiSQLProvider.class, method = "getByKey")
	Api getByKey(String key);
	
	@Override
	@UpdateProvider(type = ApiSQLProvider.class, method = "update")
	void update(Api model);
	
	@Override
	@DeleteProvider(type = ApiSQLProvider.class, method = "delete")
	void delete(String key);
}
