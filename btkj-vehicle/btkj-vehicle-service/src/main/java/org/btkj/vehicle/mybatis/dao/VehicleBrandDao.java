package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.vehicle.mybatis.provider.VehicleBrandSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleBrandDao extends DBMapper<Integer, VehicleBrand> {
	
	@Override
	@SelectProvider(type = VehicleBrandSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(VehicleBrand model);

	@MapKey("id")
	@SelectProvider(type = VehicleBrandSQLProvider.class, method = "getAll")
	Map<Integer, VehicleBrand> getAll();
	
	@Override
	@UpdateProvider(type = VehicleBrandSQLProvider.class, method = "update")
	void update(VehicleBrand model);
}
