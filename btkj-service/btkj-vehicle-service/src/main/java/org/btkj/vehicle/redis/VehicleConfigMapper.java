package org.btkj.vehicle.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.VehicleConfig;
import org.btkj.vehicle.persistence.dao.VehicleConfigDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class VehicleConfigMapper extends RedisProtostuffDBMapper<Integer, VehicleConfig, VehicleConfigDao> {

	public VehicleConfigMapper() {
		super(BtkjTables.VEHICLE_CONFIG, "hash:db:vehicle_lane");
	}

	
}
