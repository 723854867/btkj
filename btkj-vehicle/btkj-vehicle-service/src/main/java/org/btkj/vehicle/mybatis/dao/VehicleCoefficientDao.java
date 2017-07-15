package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.vehicle.mybatis.provider.VehicleCoefficientSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleCoefficientDao extends DBMapper<Integer, VehicleCoefficient> {
	
	@Override
	@InsertProvider(type = VehicleCoefficientSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(VehicleCoefficient model);
	
	@Override
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "getByKey")
	VehicleCoefficient getByKey(Integer key);
	
	@MapKey("id")
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "getByTid")
	Map<Integer, VehicleCoefficient> getByTid(int tid);
	
	@MapKey("id")
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "getByTidAndTypeForUpdate")
	Map<Integer, VehicleCoefficient> getByTidAndTypeForUpdate(@Param("tid") int tid, @Param("type") int type);
	
	@Override
	@UpdateProvider(type = VehicleCoefficientSQLProvider.class, method = "update")
	void update(VehicleCoefficient model);
	
	@Override
	@SelectProvider(type = VehicleCoefficientSQLProvider.class, method = "delete")
	void delete(Integer key);
}
