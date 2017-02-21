package org.btkj.test.service.user;

import javax.annotation.Resource;

import org.btkj.test.BaseTest;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest extends BaseTest {

	@Resource
	private UserService userService;
	
	@Test
	public void getTest() {
		User po = userService.getById(10);
		Assert.assertEquals(po.getUid(), 100);
	}
}
