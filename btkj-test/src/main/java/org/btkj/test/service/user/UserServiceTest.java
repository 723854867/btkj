package org.btkj.test.service.user;

import javax.annotation.Resource;

import org.btkj.test.BaseTest;
import org.btkj.user.api.BtkjUserService;
import org.junit.Test;

public class UserServiceTest extends BaseTest {

	@Resource
	private BtkjUserService userService;
	
	@Test
	public void getTest() {
	}
}
