package org.btkj.lebaoba.vehicle;

import javax.annotation.Resource;

import org.btkj.lebaoba.BaseTest;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.junit.Test;

public class LeBaoBaVehicleTest extends BaseTest {

	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		leBaoBaVehicle.vehicleInfos("WBSDX9108BE370935");
	}
}
