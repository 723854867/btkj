package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.vehicle.mybatis.provider.VehicleDeptSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleDeptDao extends DBMapper<Integer, VehicleDept> {

	@SelectProvider(type = VehicleDeptSQLProvider.class, method = "getByBrandId")
	List<VehicleDept> getByBrandId(int brandId);
}
