package org.btkj.config.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.ApiSQLProvider;
import org.btkj.config.pojo.entity.Api;
import org.rapid.data.storage.mapper.DBMapper;

public interface ApiDao extends DBMapper<Integer, Api> {
	
	@Override
	@InsertProvider(type = ApiSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Api model);
	
	@Override
	@SelectProvider(type = ApiSQLProvider.class, method = "getByKey")
	Api getByKey(Integer key);

	@MapKey("id")
	@SelectProvider(type = ApiSQLProvider.class, method = "getByModularId")
	Map<Integer, Api> getByModularId(int modularId);
	
	@Override
	@UpdateProvider(type = ApiSQLProvider.class, method = "update")
	void update(Api model);
	
	@Override
	@DeleteProvider(type = ApiSQLProvider.class, method = "delete")
	void delete(Integer key);
}
