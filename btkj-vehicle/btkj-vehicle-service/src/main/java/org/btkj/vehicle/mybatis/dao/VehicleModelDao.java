package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.vehicle.mybatis.provider.VehicleModelSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleModelDao extends DBMapper<Integer, VehicleModel> {

	@SelectProvider(type = VehicleModelSQLProvider.class, method = "getByDeptId")
	List<VehicleModel> getByDeptId(int deptId);
}
