package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.info.AppMainPageInfo;
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
	
	/**
	 * 释放用户锁
	 * 
	 * @param lockId
	 * @param user
	 */
	void releaseUserLock(String lockId, int uid);
	
	/**
	 * 首页信息
	 * 
	 * @param token
	 * @param app
	 * @param tenant
	 * @return
	 */
	Result<AppMainPageInfo> mainPage(App app, Tenant tenant, String token);
}
