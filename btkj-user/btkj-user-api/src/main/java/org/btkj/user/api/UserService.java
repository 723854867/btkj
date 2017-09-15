package org.btkj.user.api;

import java.util.Collection;
import java.util.Map;

import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserHolder;
import org.btkj.pojo.param.user.CustomerEditParam;
import org.btkj.pojo.param.user.CustomerListParam;
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
	User user(int uid);
	
	/**
	 * 一次获取多个用户
	 * 
	 * @param uids
	 * @return
	 */
	Map<Integer, User> users(Collection<Integer> uids);
	
	UserHolder userByToken(Client client, String token);
	
	/**
	 * 锁定用户然后获取用户锁
	 * 
	 * @param client
	 * @param mobile
	 * @return
	 */
	Result<UserHolder> userLockByToken(Client client, String token);
	
	/**
	 * 通过账号和 appId 获取用户并且获取用户锁
	 * 
	 * @param appId
	 * @param mobile
	 * @return
	 */
	Result<User> userLockByMobile(int appId, String mobile);
	
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
	Result<Void> update(User user);
	
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
	 * 根据客户 ID 获取客户
	 * 
	 * @param customerId
	 * @return
	 */
	Customer getCustomerById(long customerId);
	
	/**
	 * 客户分页
	 * 
	 * @param param
	 * @return
	 */
	Result<Pager<Customer>> customers(CustomerListParam param);
	
	Result<Void> customerEdit(CustomerEditParam param);
	
	/**
	 * 用户禁用
	 * 
	 * @param appId
	 * @param uid
	 * @return
	 */
	Result<Void> seal(int appId, int uid);
	
	/**
	 * 用户解禁
	 * 
	 * @param appId
	 * @param uid
	 * @return
	 */
	Result<Void> unseal(int appId, int uid);
}
