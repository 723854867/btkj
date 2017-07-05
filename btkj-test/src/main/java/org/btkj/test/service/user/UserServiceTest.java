package org.btkj.test.service.user;

import javax.annotation.Resource;

import org.btkj.test.BaseTest;
import org.btkj.test.persistence.dao.UserDao;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.junit.Test;

public class UserServiceTest extends BaseTest {
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void testCount() {
		userDao.count(new TenantSearcher());
	}
}
