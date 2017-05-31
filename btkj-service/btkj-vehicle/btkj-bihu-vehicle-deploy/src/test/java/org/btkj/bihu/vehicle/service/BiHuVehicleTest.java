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
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.junit.Test;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;

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
		Result<Renewal> result = biHuVehicle.renewal(form, "LBVFR7908BSE50921", "05207709", "王彬");
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
			VehicleOrderSubmit submit = new VehicleOrderSubmit();
			submit.setRenewal(renewal);
			submit.getRenewal().getSchema().getCommercial().setStart(String.valueOf(DateUtils.currentTime()));
			submit.getRenewal().getSchema().getCompulsive().setStart(String.valueOf(DateUtils.currentTime()));
			Set<Integer> set = new HashSet<Integer>();
			Result<Void> result2 = biHuVehicle.order(form, set, set, renewal);
			System.out.println(result2.getCode());
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
		tenant.setTid(1);
		tenant.setRegion(330100);
		form.setTenant(tenant);
		Result<InsuranceSchema> result = biHuVehicle.quoteResult(form, "浙AM396M", 4);
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
			VehicleOrderSubmit submit = new VehicleOrderSubmit();
			submit.setRenewal(renewal);
			submit.getRenewal().getSchema().getCommercial().setStart(String.valueOf(DateUtils.currentTime()));
			submit.getRenewal().getSchema().getCompulsive().setStart(String.valueOf(DateUtils.currentTime()));
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
		Result<PolicyDetail> result = biHuVehicle.insureResult(form, "浙H0155R", 4);
		TimeUnit.HOURS.sleep(1);
	}
}
