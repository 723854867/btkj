package org.btkj.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.entity.VehicleConfig;
import org.btkj.vehicle.mybatis.provider.VehicleConfigSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface VehicleConfigDao extends Dao<Integer, VehicleConfig> {

	@Override
	@SelectProvider(type = VehicleConfigSQLProvider.class, method = "selectByKey")
	VehicleConfig selectByKey(Integer key);
}
