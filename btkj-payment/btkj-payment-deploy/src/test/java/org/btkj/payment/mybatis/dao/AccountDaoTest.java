package org.btkj.payment.mybatis.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.payment.BaseTest;
import org.btkj.pojo.entity.payment.Account;
import org.junit.Test;

public class AccountDaoTest extends BaseTest {

	@Resource
	private AccountDao accountDao;
	
	@Test
	public void testReplace() {
		Map<Integer, Account> map = new HashMap<>();
		map.put(1, new Account());
		accountDao.replace(map);
	}
}
