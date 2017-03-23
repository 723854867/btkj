package org.btkj.user.api;

import org.btkj.pojo.entity.User;

/**
 * 用户服务
 * 
 * @author ahab
 */
public interface UserService {

	/**
	 * 通过用户 uid 获取 用户
	 * 
	 * @param uid
	 * @return
	 */
	User getUserByUid(int uid);
}
