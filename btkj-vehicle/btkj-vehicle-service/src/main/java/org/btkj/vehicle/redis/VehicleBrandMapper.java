package org.btkj.vehicle.redis;

import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.vehicle.mybatis.dao.VehicleBrandDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class VehicleBrandMapper extends RedisDBAdapter<Integer, VehicleBrand, VehicleBrandDao> {
	
	private String LOAD_LOCK						= "lock:vehicle_brand";							

	public VehicleBrandMapper() {
		super(new ByteProtostuffSerializer<VehicleBrand>(), "hash:db:vehicle_brand");
	}
}
