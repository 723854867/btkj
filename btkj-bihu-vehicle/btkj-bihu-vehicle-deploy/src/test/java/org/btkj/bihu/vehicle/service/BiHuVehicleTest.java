package org.btkj.bihu.vehicle.service;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.BaseTest;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.pojo.po.Renewal;
import org.junit.Test;
import org.rapid.util.common.message.Result;

public class BiHuVehicleTest extends BaseTest {

	@Resource
	private BiHuVehicle biHuVehicle;
	
	@Test
	public void testRenewal() {
		Result<Renewal> result = biHuVehicle.renewal(1, 4, "皖J00740", 9);
		System.out.println(result);
	}
}
