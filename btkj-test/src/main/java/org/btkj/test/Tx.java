package org.btkj.test;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.btkj.pojo.entity.User;
import org.btkj.test.persistence.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Tx {

	@Resource
	private UserDao userDao;
	
	@Transactional
	public void selectForUpdate(int appId) throws InterruptedException {
		System.out.println("start to selectForUpdate");
//		userDao.selectForUpdate(appId);
		TimeUnit.SECONDS.sleep(10);
		System.out.println("selectForUpdate finish");
	}
	
	public void selectByAppId(int appId) throws InterruptedException {
//		List<User> list = userDao.selectByAppId(appId);
		TimeUnit.SECONDS.sleep(3);
		System.out.println("selectByAppId finish");
	}
	
	public void insert(User user) throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("start to insert");
		userDao.insert(user);
		System.out.println("insert finish");
	}
}
