package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.vehicle.BaseTest;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.junit.Test;

public class VehiclePolicyMapperTest extends BaseTest {

	@Resource
	private VehiclePolicyMapper vehiclePolicyMapper;
	
	@Test
	public void testBatchInsert() {
		List<VehiclePolicy> policies = new ArrayList<VehiclePolicy>();
		for (int i = 0; i < 100; i++) {
			VehiclePolicy policy = new VehiclePolicy(i, i, i + 10);
			policy.setName("轿车" + i);
			policy.setLicense("浙H0155R" + i);
			policies.add(policy);
		}
		vehiclePolicyMapper.batchInsert(policies);
	}
}
