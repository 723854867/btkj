package org.btkj.test;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.btkj.test.persistence.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring/*.xml")
public class BaseTest {
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void test() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				userDao.selectForUpdate(100);
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
