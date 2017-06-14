package org.btkj.config.mongo;

import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficientCategory;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.KeyMapper;

public class VehicleCoefficientCategoryMapper extends MongoMapper<Long, VehicleCoefficientCategory> {
	
	private KeyMapper keyMapper;

	public VehicleCoefficientCategoryMapper() {
		super("vehicle_coefficient_category");
	}
	
	@Override
	public VehicleCoefficientCategory insert(VehicleCoefficientCategory model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection, 1));
		return super.insert(model);
	}

	public List<VehicleCoefficientCategory> getAll() {
		return mongo.find(collection, VehicleCoefficientCategory.class);
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
