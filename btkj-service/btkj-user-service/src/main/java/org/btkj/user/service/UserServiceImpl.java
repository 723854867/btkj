package org.btkj.user.service;

import org.btkj.user.api.UserService;
import org.btkj.user.pojo.entity.User;

public class UserServiceImpl implements UserService {

	@Override
	public User getById(int id) {
		System.out.println("local");
		User po = new User();
		po.setUid(100);
		return po;
	}
}
