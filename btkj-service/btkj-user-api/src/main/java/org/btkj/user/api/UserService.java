package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.ClientType;
import org.rapid.util.common.message.Result;

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
	
	/**
	 * 通过 token 获取用户
	 * 
	 * @param ct
	 * @param token
	 * @return
	 */
	User getUserByToken(ClientType ct, App app, String token);
	
	/**
	 * 通过 token 获取用户并且获取用户锁
	 * 
	 * @param ct
	 * @param token
	 * @return
	 */
	Result<User> lockUserByToken(ClientType ct, App app, String token);
}
