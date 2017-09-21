package org.btkj.bihu.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.BaseTest;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.vehicle.Renewal;
import org.btkj.pojo.info.VehicleInfo;
import org.junit.Test;
import org.rapid.util.common.message.Result;

public class BiHuVehicleTest extends BaseTest {

	@Resource
	private BiHuVehicle biHuVehicle;
	
	@Test
	public void testRenewal() {
		Tenant tenant = new Tenant();
		tenant.setBiHuAgent("92352");
		tenant.setBiHuKey("71055b7e91b");
		Result<Renewal> result = biHuVehicle.renewal(new Tenant(), 1, "浙A888XQ", 9);
		System.out.println(result);
	}
	
	@Test
	public void testVehicleInfo() {
		Result<List<VehicleInfo>> result = biHuVehicle.vehicleInfos(1, new Tenant(), "浙H0155R", "雪佛兰SGM7184ATB", 9);
		System.out.println(result);
	}
}
