package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.vehicle.mybatis.provider.VehicleCoefficientSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleCoefficientDao extends DBMapper<Integer, VehicleCoefficient> {
	
	@Override
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "getByKey")
	VehicleCoefficient getByKey(Integer key);
	
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "getByTid")
	List<VehicleCoefficient> getByTid(int tid);
	
	@Override
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "delete")
	void delete(Integer key);
}
