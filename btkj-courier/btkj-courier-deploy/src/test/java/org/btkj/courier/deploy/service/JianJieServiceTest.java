package org.btkj.courier.deploy.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.courier.deploy.BaseTest;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.junit.Test;
import org.rapid.util.common.serializer.SerializeUtil;

public class JianJieServiceTest extends BaseTest{
	
	@Resource
	private JianJieService jianJieService;
	
	@Test
	public void testAddUser() throws InterruptedException {
		UserPO user = new UserPO();
		user.setUid(1);
		user.setName("test");
		user.setIdentity("11012719870603341X");
		jianJieService.addUser(user);
		TimeUnit.HOURS.sleep(1);
	}

	@Test
	public void testVehiclePolices() {
		JianJiePoliciesInfo info = jianJieService.vehiclePolicies("2", "20000101", "20170609");
		System.out.println(SerializeUtil.JsonUtil.GSON.toJson(info));
	}
}
