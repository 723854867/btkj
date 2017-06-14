package org.btkj.courier.deploy.service;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.courier.deploy.BaseTest;
import org.btkj.courier.model.JianJiePoliciesInfo;
import org.junit.Test;

public class JianJieServiceTest extends BaseTest{
	
	@Resource
	private JianJieService jianJieService;

	@Test
	public void testVehiclePolices() {
		JianJiePoliciesInfo info = jianJieService.vehiclePolicies("2", "20000101", "20170609");
		System.out.println(info);
	}
}
