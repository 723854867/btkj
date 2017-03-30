package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.persistence.provider.TenantSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface TenantDao extends Dao<Integer, Tenant> {
	
	@Override
	@InsertProvider(type = TenantSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "tid", keyProperty = "tid")
	void insert(Tenant tenant);
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "selectAll")
	List<Tenant> selectAll();
	
	@Override
	@SelectProvider(type = TenantSQLProvider.class, method = "selectByKey")
	Tenant selectByKey(Integer key);
}
