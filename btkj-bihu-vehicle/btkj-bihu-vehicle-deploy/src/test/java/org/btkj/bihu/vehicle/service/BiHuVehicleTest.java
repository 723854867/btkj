package org.btkj.bihu.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.BaseTest;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.pojo.po.Renewal;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.VehicleInfo;
import org.junit.Test;
import org.rapid.util.common.message.Result;

public class BiHuVehicleTest extends BaseTest {

	@Resource
	private BiHuVehicle biHuVehicle;
	
	@Test
	public void testRenewal() {
		Result<Renewal> result = biHuVehicle.renewal(new TenantPO(), 1, "浙H0155R", 9);
		System.out.println(result);
	}
	
	@Test
	public void testVehicleInfo() {
		Result<List<VehicleInfo>> result = biHuVehicle.vehicleInfos(1, new TenantPO(), "浙H0155R", "雪佛兰SGM7184ATB", 9);
		System.out.println(result);
	}
}
