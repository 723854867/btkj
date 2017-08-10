package org.btkj.user.mybatis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.mybatis.provider.TenantSQLProvider;
import org.btkj.user.pojo.param.TenantsParam;
import org.rapid.data.storage.mapper.DBMapper;

public interface TenantDao extends DBMapper<Integer, TenantPO> {
	
	@Override
	@InsertProvider(type = TenantSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "tid", keyProperty = "tid")
	void insert(TenantPO tenant);
	
	@Override
	@UpdateProvider(type = TenantSQLProvider.class, method = "update")
	void update(TenantPO entity);
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "getByKey")
	TenantPO getByKey(Integer key);
	
	@Override
	@MapKey("tid")
	@SelectProvider(type = TenantSQLProvider.class, method = "getByKeys")
	Map<Integer, TenantPO> getByKeys(Collection<Integer> keys);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppId")
	int countByAppId(int appId);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppIdForUpdate")
	int countByAppIdForUpdate(int appId);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "count")
	int count(TenantsParam param);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "tenants")
	List<TenantPO> tenants(TenantsParam param);
}
