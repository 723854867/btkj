package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
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
	 * 通过雇员 ID 获取用户
	 * 
	 * @return
	 */
	User getUserByEmployeeId(int employeeId);
	
	/**
	 * 通过 token 获取用户
	 * 
	 * @param ct
	 * @param token
	 * @return
	 */
	User getUserByToken(Client ct, String token);
	
	/**
	 * 通过 token 获取用户并且获取用户锁
	 * 
	 * @param ct
	 * @param token
	 * @return
	 */
	Result<User> lockUserByToken(Client ct, String token);
	
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
	Result<IMainPageInfo> mainPage(Client client, String token, Tenant tenant);
	
	/**
	 * 我的团队信息
	 * 
	 * @param app
	 * @param tenant
	 * @param token
	 * @return
	 */
	Result<?> teamInfo(App app, Tenant tenant, String token);
	
	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	Result<?> update(User user);
	
	/**
	 * 密码重置
	 * 
	 * @param appId
	 * @param mobile
	 * @param pwd
	 * @return
	 */
	Result<?> pwdReset(int appId, String mobile, String pwd);
}
