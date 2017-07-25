package org.btkj.vehicle.mongo;

import java.util.List;

import org.btkj.pojo.bo.Pager;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

@Component("vehiclePolicyMapper")
public class VehiclePolicyMapper extends MongoMapper<String, VehiclePolicy> {

	public VehiclePolicyMapper() {
		super("vehiclePolicy");
	}
	
	public void batchInsert(List<VehiclePolicy> policies) {
		mongo.insertMany(collection, policies);
	}
	
	public Pager<VehiclePolicy> paging(VehiclePolicySearcher searcher) {
		return null;
	}
}
