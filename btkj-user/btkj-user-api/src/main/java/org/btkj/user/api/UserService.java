package org.btkj.user.api;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserHolder;
import org.btkj.pojo.model.identity.User;
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
	UserPO user(int uid);
	
	/**
	 * 一次获取多个用户
	 * 
	 * @param uids
	 * @return
	 */
	Map<Integer, UserPO> users(Collection<Integer> uids);
	
	/**
	 * 通过账号和 appId 获取用户
	 * 
	 * @param mobile
	 * @param appId
	 * @return
	 */
	User user(String mobile, int appId);
	
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
	Result<UserPO> userLockByMobile(int appId, String mobile);
	
	/**
	 * 通过 token 获取用户
	 * 
	 * @param client
	 * @param toke
	 * @return
	 */
	User getUserByToken(Client client, String token);
	
	/**
	 * 获取用户的同时获取用户锁
	 * 
	 * @param client
	 * @param token
	 * @return
	 */
	Result<User> lockUserByToken(Client client, String token);

	/**
	 * 通过雇员 ID 获取用户
	 * 
	 * @return
	 */
	UserPO getUserByEmployeeId(int employeeId);
	
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
	Result<Void> update(UserPO user);
	
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
	
	/**
	 * 新增客户
	 * 
	 * @param uid
	 * @param name
	 * @param identity
	 * @param mobile
	 * @param license
	 * @param regions
	 * @param address
	 * @param memo
	 * @return
	 */
	Result<Void> customerAdd(int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo);

	/**
	 * 修改客户
	 * 
	 * @param id
	 * @param uid
	 * @param name
	 * @param identity
	 * @param mobile
	 * @param license
	 * @param regions
	 * @param address
	 * @param memo
	 * @return
	 */
	Result<Void> customerUpdate(long id, int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo);
	
	/**
	 * 删除客户
	 * 
	 * @param id
	 * @param uid
	 * @return
	 */
	Result<Void> customerDelete(long id, int uid);
	
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
