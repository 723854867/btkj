package org.btkj.user.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.po.AppPO;
import org.btkj.user.mybatis.provider.AppSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface AppDao extends DBMapper<Integer, AppPO> {
	
	@Override
	@InsertProvider(type = AppSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(AppPO entity);
	
	@MapKey("id")
	@SelectProvider(type = AppSQLProvider.class, method = "getAll")
	Map<Integer, AppPO> getAll();
	
	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "getByKey")
	AppPO getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = AppSQLProvider.class, method = "getByKeys")
	Map<Integer, AppPO> getByKeys(Collection<Integer> keys);
	
	
	@SelectProvider(type = AppSQLProvider.class, method = "getByKeyForUpdate")
	AppPO getByKeyForUpdate(Integer key);
	
	@Override
	@UpdateProvider(type = AppSQLProvider.class, method = "update")
	void update(AppPO entity);
}
