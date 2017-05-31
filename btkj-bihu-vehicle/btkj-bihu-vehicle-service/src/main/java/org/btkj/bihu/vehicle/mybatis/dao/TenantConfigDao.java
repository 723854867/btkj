package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.TenantConfig;
import org.btkj.bihu.vehicle.mybatis.provider.TenantConfigSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface TenantConfigDao extends Dao<Integer, TenantConfig> {

	@Override
	@SelectProvider(type = TenantConfigSQLProvider.class, method = "selectByKey")
	TenantConfig selectByKey(Integer tid);
}
