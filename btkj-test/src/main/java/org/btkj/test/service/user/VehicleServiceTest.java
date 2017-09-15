package org.btkj.test.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.test.BaseTest;
import org.btkj.vehicle.api.VehicleService;
import org.junit.Test;

public class VehicleServiceTest extends BaseTest {

	@Resource
	private VehicleService vehicleService;
	
	@Test
	public void test() {
		List<VehicleInfo> infos = vehicleService.vehicleInfos(new Tenant(), "WBSDX9108BE370935");
		System.out.println(infos);
	}
}
