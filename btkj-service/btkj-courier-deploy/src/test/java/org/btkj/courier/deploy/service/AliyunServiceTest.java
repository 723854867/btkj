package org.btkj.courier.deploy.service;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.courier.deploy.BaseTest;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.StsInfo;
import org.junit.Test;

public class AliyunServiceTest extends BaseTest {
	
	@Resource
	private AliyunService aliyunService;

	@Test
	public void testAssumeRole() { 
		User user = new User();
		user.setAppId(100);
		user.setUid(1);
		StsInfo stsInfo = aliyunService.assumeRole(user);
	}
}
