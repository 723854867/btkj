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
	@SelectProvider(type = ApiSQLProvider.class, method = "getByKey")
	Api getByKey(String key);

	@MapKey("pkg")
	@SelectProvider(type = ApiSQLProvider.class, method = "getByModularId")
	Map<String, Api> getByModularId(int modularId);
	
	@Override
	@UpdateProvider(type = ApiSQLProvider.class, method = "update")
	void update(Api model);
	
	@Override
	@DeleteProvider(type = ApiSQLProvider.class, method = "delete")
	void delete(String key);
}
