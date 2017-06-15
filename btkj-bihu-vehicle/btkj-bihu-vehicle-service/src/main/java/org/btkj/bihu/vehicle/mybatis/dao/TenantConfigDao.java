package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.TenantConfig;
import org.btkj.bihu.vehicle.mybatis.provider.TenantConfigSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface TenantConfigDao extends DBMapper<Integer, TenantConfig> {

	@Override
	@SelectProvider(type = TenantConfigSQLProvider.class, method = "getByKey")
	TenantConfig getByKey(Integer tid);
}
