package org.btkj.bihu.vehicle.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.bihu.vehicle.deploy.BaseTest;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.junit.Test;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;

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
		Result<Renewal> result = biHuVehicle.renewal(form, "浙H0155R", "江晨辰");
		System.out.println(result.getCode());
		TimeUnit.HOURS.sleep(2);
	}
	
	@Test
	public void testRenewInfoByVin() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(1);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(1);
		tenant.setRegion(110000);
		form.setTenant(tenant);
		Result<Renewal> result = biHuVehicle.renewal(form, "LBVFR7908BSE50921", "05207709");
		System.out.println(result.getCode());
		TimeUnit.HOURS.sleep(2);
	}
	
	@Test
	public void quoteTest() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(3);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(4);
		tenant.setRegion(330100);
		form.setTenant(tenant);
		Result<Renewal> result = biHuVehicle.renewal(form, "浙AM396M", "王彬");
		if (result.isSuccess()) {
			Renewal renewal = result.attach();
			renewal.getTips().getSchema().setCommercialStart(String.valueOf(DateUtil.currentTime()));
			renewal.getTips().getSchema().setCompulsiveStart(String.valueOf(DateUtil.currentTime()));
			Set<Integer> set = new HashSet<Integer>();
			set.add(3);
//			Result<Void> result2 = biHuVehicle.order(form, set, set, renewal.getTips());
//			System.out.println(result2.getCode());
		} else 
			System.out.println(result.getCode());
		TimeUnit.HOURS.sleep(1);
	}
	
	@Test
	public void quoteResultTest() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(3);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(4);
		tenant.setRegion(330100);
		form.setTenant(tenant);
		Result<PolicySchema> result = biHuVehicle.quoteResult(form, "浙AM396M", 3);
		System.out.println(result.attach());
		TimeUnit.HOURS.sleep(1);
	}
	
	@Test
	public void insureTest() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(3);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(1);
		tenant.setRegion(330100);
		form.setTenant(tenant);
		Result<Renewal> result = biHuVehicle.renewal(form, "浙H0155R", "");
		if (result.isSuccess()) {
			Renewal renewal = result.attach();
			renewal.getTips().getSchema().setCommercialStart(String.valueOf(DateUtil.currentTime()));
			renewal.getTips().getSchema().setCompulsiveStart(String.valueOf(DateUtil.currentTime()));
		} else 
			System.out.println(result.getCode());
		TimeUnit.HOURS.sleep(1);
	}
	
	@Test
	public void insureResultTest() throws InterruptedException {
		EmployeeForm form = new EmployeeForm();
		User user = new User();
		user.setUid(3);
		form.setUser(user);
		Tenant tenant = new Tenant();
		tenant.setTid(1);
		tenant.setRegion(330100);
		form.setTenant(tenant);
//		Result<PolicyDetail> result = biHuVehicle.insureResult(form, "浙H0155R", 4);
		TimeUnit.HOURS.sleep(1);
	}
}
