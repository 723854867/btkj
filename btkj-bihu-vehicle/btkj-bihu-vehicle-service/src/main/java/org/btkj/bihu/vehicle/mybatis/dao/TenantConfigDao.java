package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.provider.TenantConfigSQLProvider;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface TenantConfigDao extends DBMapper<Integer, TenantConfig> {

	@Override
	@SelectProvider(type = TenantConfigSQLProvider.class, method = "getByKey")
	TenantConfig getByKey(Integer tid);
	
	@DeleteProvider(type = TenantConfigSQLProvider.class, method = "delete")
	void delete(int tid); 
}
