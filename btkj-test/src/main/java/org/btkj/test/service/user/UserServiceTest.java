package org.btkj.test.service.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.test.BaseTest;
import org.btkj.test.persistence.dao.AppDao;
import org.btkj.test.persistence.dao.UserDao;
import org.junit.Test;

public class UserServiceTest extends BaseTest {
	
	@Resource
	private AppDao appDao;
	@Resource
	private UserDao userDao;
	
	@Test
	public void testGetAll() {
		Map<Integer, User> map = userDao.getAll();
		for (Entry<Integer, User> entry : map.entrySet()) {
			System.out.println(entry.getValue().getAppId() + " " + entry.getValue().getMobile() + " " + entry.getValue().getUid());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		map = userDao.getByKeys(set);
		for (Entry<Integer, User> entry : map.entrySet()) {
			System.out.println(entry.getValue().getAppId() + " " + entry.getValue().getMobile() + " " + entry.getValue().getUid());
		}
	}
	
	@Test
	public void testAppGet() {
//		Map<Integer, User> amp1 = userDao.getAll();
//		for (Entry<Integer, User> entry : amp1.entrySet()) {
//			System.out.println(entry.getValue().getAppId() + " " + entry.getValue().getMobile() + " " + entry.getValue().getUid());
//		}
//		Set<Integer> set1 = new HashSet<Integer>();
//		set1.add(1);
//		set1.add(2);
//		amp1 = userDao.getByKeys(set1);
//		for (Entry<Integer, User> entry : amp1.entrySet()) {
//			System.out.println(entry.getValue().getAppId() + " " + entry.getValue().getMobile() + " " + entry.getValue().getUid());
//		}
		
		Map<Integer, App> map = appDao.getAll();
		for (Entry<Integer, App> entry : map.entrySet()) {
			System.out.println(entry.getValue().getId() + " " + entry.getValue().getName() + " " + entry.getValue().getRegion());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		map = appDao.getByKeys(set);
		for (Entry<Integer, App> entry : map.entrySet()) {
			System.out.println(entry.getValue().getId() + " " + entry.getValue().getName() + " " + entry.getValue().getRegion());
		}
	}
	
	@Test
	public void testGetByKey() {
		User user = userDao.getByKey(1);
		System.out.println(user.getAppId() + " " + user.getName() + " " + user.getMobile());
	}
	
	@Test
	public void testDelete() {
		appDao.delete(2);
	}
}
