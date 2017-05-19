package org.btkj.bihu.vehicle.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.bihu.vehicle.deploy.BaseTest;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.EmployeeForm;
import org.junit.Test;

public class BiHuVehicleTest extends BaseTest {

	@Resource
	private BiHuVehicle biHuVehicle;
	
	@Test
	public void testRenewInfo() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(1);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(1);
		tenant.setRegion(110000);
		form.setTenant(tenant);
		biHuVehicle.renewlInfo(form, "浙H0155R");
		TimeUnit.HOURS.sleep(2);
	}
}
