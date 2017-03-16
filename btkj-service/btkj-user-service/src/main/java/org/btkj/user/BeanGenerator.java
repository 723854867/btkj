package org.btkj.user;

import org.btkj.pojo.entity.User;
import org.btkj.user.model.CreateMarker;
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
	public static final User newUser(CreateMarker cm, String identity, String name) { 
		User user = new User();
		user.setAppId(cm.getAppId());
		user.setMobile(cm.getMobile());
		user.setIdentity(identity);
		user.setName(name);
		
		int time = DateUtils.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		user.setLastLoginTime(time);
		return user;
	}
}
