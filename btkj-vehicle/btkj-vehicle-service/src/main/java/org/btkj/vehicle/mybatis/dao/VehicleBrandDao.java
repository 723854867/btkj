package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.vehicle.mybatis.provider.VehicleBrandSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleBrandDao extends DBMapper<Integer, VehicleBrand> {

	@MapKey("id")
	@SelectProvider(type = VehicleBrandSQLProvider.class, method = "getAll")
	Map<Integer, VehicleBrand> getAll();
}
