package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.TenantListPc;
import org.btkj.pojo.submit.TenantSearcher;
import org.btkj.user.persistence.provider.TenantSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface TenantDao extends Dao<Integer, Tenant> {
	
	@Override
	@InsertProvider(type = TenantSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "tid", keyProperty = "tid")
	void insert(Tenant tenant);
	
	@Override
	@UpdateProvider(type = TenantSQLProvider.class, method = "update")
	void update(Tenant entity);
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "selectAll")
	List<Tenant> selectAll();
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "selectByKey")
	Tenant selectByKey(Integer key);
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "selectWithinKey")
	List<Tenant> selectWithinKey(@Param("list") List<Integer> keys);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppId")
	int countByAppId(int appId);
	
	@SelectProvider(type = TenantSQLProvider.class, method = "countByAppIdForUpdate")
	int countByAppIdForUpdate(int appId);
	
	int searchCount(TenantSearcher searcher);
	
	List<TenantListPc> search(TenantSearcher searcher);
}
