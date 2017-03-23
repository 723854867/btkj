package org.btkj.user.api;

import org.btkj.pojo.entity.User;

/**
 * 用户服务
 * 
 * @author ahab
 */
public interface UserService {

	/**
	 * 通过 uid 获取用户
	 * 
	 * @param uid
	 * @return
	 */
	User getUser(int uid);
}
