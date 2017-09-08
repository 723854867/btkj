package org.btkj.test.service.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
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
		Map<Integer, UserPO> map = userDao.getAll();
		for (Entry<Integer, UserPO> entry : map.entrySet()) {
			System.out.println(entry.getValue().getAppId() + " " + entry.getValue().getMobile() + " " + entry.getValue().getUid());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		map = userDao.getByKeys(set);
		for (Entry<Integer, UserPO> entry : map.entrySet()) {
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
		
		Map<Integer, AppPO> map = appDao.getAll();
		for (Entry<Integer, AppPO> entry : map.entrySet()) {
			System.out.println(entry.getValue().getId() + " " + entry.getValue().getName() + " " + entry.getValue().getRegion());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		map = appDao.getByKeys(set);
		for (Entry<Integer, AppPO> entry : map.entrySet()) {
			System.out.println(entry.getValue().getId() + " " + entry.getValue().getName() + " " + entry.getValue().getRegion());
		}
	}
	
	@Test
	public void testGetByKey() {
		UserPO user = userDao.getByKey(1);
		System.out.println(user.getAppId() + " " + user.getName() + " " + user.getMobile());
	}
	
	@Test
	public void testDelete() {
		appDao.delete(2);
	}
	
	@Test
	public void testInsert() {
		UserPO user = new UserPO();
		user.setAppId(10);
		userDao.insert(user);
	}
	
	@Test
	public void testUpdate() {
		UserPO user = new UserPO();
		user.setUid(60);
		user.setAppId(1);
		user.setMobile("+8617826877008");
		user.setCreated(10);
		user.setUpdated(11);
		userDao.update(user);
	}
	
	@Test
	public void testBatchInsert() {
		List<UserPO> users = new ArrayList<UserPO>();
		for (int i = 0; i < 3; i++) {
			UserPO user = new UserPO();
			user.setAppId(i);
			user.setMobile("+sss");
			user.setCreated(i);
			user.setUpdated(i);
			users.add(user);
		}
		userDao.batchInsert(users);
		System.out.println(users);
	}
}
