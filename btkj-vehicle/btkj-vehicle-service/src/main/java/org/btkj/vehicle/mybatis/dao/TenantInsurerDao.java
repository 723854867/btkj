package org.btkj.vehicle.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.vehicle.mybatis.provider.TenantInsurerSQLProvider;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.rapid.data.storage.mapper.DBMapper;

public interface TenantInsurerDao extends DBMapper<String, TenantInsurer> {
	
	@Override
	@InsertProvider(type = TenantInsurerSQLProvider.class, method = "insert")
	void insert(TenantInsurer model);

	@Override
	@SelectProvider(type = TenantInsurerSQLProvider.class, method = "getByKey")
	TenantInsurer getByKey(String key);
	
	@Override
	@MapKey("key")
	@SelectProvider(type = TenantInsurerSQLProvider.class, method = "getByKeys")
	Map<String, TenantInsurer> getByKeys(Collection<String> keys);
	
	@MapKey("key")
	@SelectProvider(type = TenantInsurerSQLProvider.class, method = "getByTid")
	Map<String, TenantInsurer> getByTid(int tid);
	
	@Override
	@UpdateProvider(type = TenantInsurerSQLProvider.class, method = "update")
	void update(TenantInsurer model);
	
	@Override
	@UpdateProvider(type = TenantInsurerSQLProvider.class, method = "replace")
	void replace(Collection<TenantInsurer> collection);
	
	@DeleteProvider(type = TenantInsurerSQLProvider.class, method = "delete")
	void delete(String key);
	
	@Override
	@DeleteProvider(type = TenantInsurerSQLProvider.class, method = "deleteByKeys")
	void deleteByKeys(Collection<String> keys);
}
