package org.btkj.config.redis;

import java.util.List;

import org.btkj.config.mybatis.dao.VehicleCoefficientCategoryDao;
import org.btkj.pojo.entity.VehicleCoefficientCategory;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class VehicleCoefficientCategoryMapper extends RedisDBAdapter<Integer, VehicleCoefficientCategory, VehicleCoefficientCategoryDao> {

	public VehicleCoefficientCategoryMapper() {
		super(new ByteProtostuffSerializer<VehicleCoefficientCategory>(), "hash:db:vehicle_coefficient_category");
	}
	
	public List<VehicleCoefficientCategory> getAll() {
		return null;
	}
}
