package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.vehicle.mybatis.provider.VehicleModelSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleModelDao extends DBMapper<Integer, VehicleModel> {
	
	@Override
	@SelectProvider(type = VehicleModelSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(VehicleModel model);

	@MapKey("id")
	@SelectProvider(type = VehicleModelSQLProvider.class, method = "getByDeptId")
	Map<Integer, VehicleModel> getByDeptId(int deptId);
}
