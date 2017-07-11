package org.btkj.user.api;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.entity.Customer;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserModel;
import org.btkj.user.pojo.submit.CustomerSearcher;
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
	 * 一次获取多个用户
	 * 
	 * @param list
	 * @return
	 */
	List<User> users(List<Integer> list);
	
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
	 * 判断用户代理公司数是否达到最大值
	 * 
	 * @param user
	 * @return
	 */
	boolean tenantNumMax(User user);
	
	/**
	 * 根据客户 ID 获取客户
	 * 
	 * @param customerId
	 * @return
	 */
	Customer getCustomerById(long customerId);
	
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
	 * 客户分页
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<Customer>> customers(CustomerSearcher searcher);
}
