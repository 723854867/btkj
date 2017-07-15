package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.vehicle.mybatis.provider.VehicleDeptSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleDeptDao extends DBMapper<Integer, VehicleDept> {
	
	@Override
	@SelectProvider(type = VehicleDeptSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(VehicleDept model);

	@MapKey("id")
	@SelectProvider(type = VehicleDeptSQLProvider.class, method = "getByBrandId")
	Map<Integer, VehicleDept> getByBrandId(int brandId);
}
