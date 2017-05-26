package org.btkj.vehicle.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.vehicle.mybatis.dao.VehicleConfigDao;
import org.btkj.vehicle.mybatis.entity.VehicleConfig;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class VehicleConfigMapper extends RedisProtostuffDBMapper<Integer, VehicleConfig, VehicleConfigDao> {

	public VehicleConfigMapper() {
		super(BtkjTables.VEHICLE_CONFIG, "hash:db:vehicle_config");
	}
}
