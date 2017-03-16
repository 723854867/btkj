package org.btkj.user;

import org.btkj.pojo.entity.User;
import org.rapid.util.lang.DateUtils;

public class BeanGenerator {

	/**
	 * 新用户
	 * 
	 * @param cm
	 * @param identity
	 * @param name
	 * @return
	 */
	public static final User newUser(int appId, String mobile, String identity, String name) { 
		User user = new User();
		user.setAppId(appId);
		user.setMobile(mobile);
		user.setIdentity(identity);
		user.setName(name);
		
		int time = DateUtils.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		user.setLastLoginTime(time);
		return user;
	}
}
