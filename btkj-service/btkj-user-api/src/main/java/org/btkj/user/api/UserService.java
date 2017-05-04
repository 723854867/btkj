package org.btkj.user.api;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.UserModel;
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
	 * 通过账号和 appId 获取用户
	 * 
	 * @param mobile
	 * @param appId
	 * @return
	 */
	User getUser(String mobile, int appId);
	
	/**
	 * 通过 token 获取用户
	 * 
	 * @param client
	 * @param toke
	 * @return
	 */
	UserModel getUserByToken(Client client, String token);
	
	/**
	 * 获取用户的同时获取用户锁
	 * 
	 * @param client
	 * @param token
	 * @return
	 */
	Result<UserModel> lockUserByToken(Client client, String token);

	/**
	 * 通过雇员 ID 获取用户
	 * 
	 * @return
	 */
	User getUserByEmployeeId(int employeeId);
	
	/**
	 * 释放用户锁
	 * 
	 * @param lockId
	 * @param user
	 */
	void releaseUserLock(String lockId, int uid);
	
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
	
	/**
	 * 判断用户代理公司数是否达到最大值
	 * 
	 * @param user
	 * @return
	 */
	boolean tenantNumMax(User user);
}
