package org.btkj.user.mybatis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.mybatis.provider.TenantSQLProvider;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.rapid.data.storage.mapper.DBMapper;

public interface TenantDao extends DBMapper<Integer, Tenant> {
	
	@Override
	@InsertProvider(type = TenantSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "tid", keyProperty = "tid")
	void insert(Tenant tenant);
	
	@Override
	@UpdateProvider(type = TenantSQLProvider.class, method = "update")
	void update(Tenant entity);
	
	@MapKey("tid")
	@SelectProvider(type = TenantSQLProvider.class, method = "getAll")
	Map<Integer, Tenant> getAll();
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "getByKey")
	Tenant getByKey(Integer key);
	
	@Override
	@MapKey("tid")
	@SelectProvider(type = TenantSQLProvider.class, method = "getByKeys")
	Map<Integer, Tenant> getByKeys(@Param("keys") Collection<Integer> keys);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppId")
	int countByAppId(int appId);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppIdForUpdate")
	int countByAppIdForUpdate(int appId);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "count")
	int count(TenantSearcher searcher);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "paging")
	List<Tenant> paging(TenantSearcher searcher);
}
