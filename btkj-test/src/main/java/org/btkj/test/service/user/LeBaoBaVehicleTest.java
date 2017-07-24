package org.btkj.test.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.vo.VehicleInfo;
import org.btkj.test.BaseTest;
import org.junit.Test;

public class LeBaoBaVehicleTest extends BaseTest {

	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		List<VehicleInfo> infos = leBaoBaVehicle.vehicleInfos("WBSDX9108BE370935");
		System.out.println(infos);
	}
}
