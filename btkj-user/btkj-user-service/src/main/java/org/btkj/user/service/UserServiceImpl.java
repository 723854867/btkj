package org.btkj.user.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Customer;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserModel;
import org.btkj.user.api.UserService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.CustomerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private Redis redis;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private CustomerMapper customerMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public User getUser(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public List<User> users(List<Integer> list) {
		return userMapper.getWithinKey(list);
	}
	
	@Override
	public User getUser(String mobile, int appId) {
		return userMapper.getUserByMobile(appId, mobile);
	}
	
	@Override
	public UserModel getUserByToken(Client client, String token) {
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			if (null == uid)
				return null;
			User user = userMapper.getByKey(Integer.valueOf(uid));
			return new UserModel(appMapper.getByKey(user.getAppId()), user);
		default:
			return userMapper.getUserByToken(client, token);
		}
	}
	
	@Override
	public Result<UserModel> lockUserByToken(Client client, String token) {
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			if (null == uid)
				return null;
			User user = userMapper.getByKey(Integer.valueOf(uid));
			String lockId = userMapper.lockUser(user.getUid());
			if (null == lockId)
				return Consts.RESULT.USER_STATUS_CHANGED;
			return Result.result(Code.OK.id(), lockId, new UserModel(appMapper.getByKey(user.getAppId()), user));
		default:
			return userMapper.lockUserByToken(client, token);
		}
	}
	
	@Override
	public User getUserByEmployeeId(int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		return null == employee ? null : userMapper.getByKey(employee.getUid());
	}
	
	@Override
	public void releaseUserLock(String lockId, int uid) {
		userMapper.releaseUserLock(uid, lockId);
	}
	
	@Override
	public Result<Void> update(User user) {
		user.setUpdated(DateUtil.currentTime());
		try {
			userMapper.update(user);
		} catch (DuplicateKeyException e) {
			return Result.result(Code.IDENTITY_ALREADY_EXIST);
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<?> pwdReset(int appId, String mobile, String pwd) {
		Result<User> result = userMapper.lockUserByMobile(appId, mobile);
		if (!result.isSuccess())
			return result;
		try {
			User user = result.attach();
			user.setPwd(pwd);
			userMapper.update(user);
			return Result.success();
		} finally {
			userMapper.releaseUserLock(result.attach().getUid(), result.getDesc());
		}
	}
	
	@Override
	public boolean tenantNumMax(User user) {
		int limit = employeeMapper.ownedTenants(user).size() + applyMapper.applyListTids(user).size();
		return limit >= GlobalConfigContainer.getGlobalConfig().getMaxTenantNum();
	}
	
	@Override
	public Customer getCustomerById(long customerId) {
		return customerMapper.getByKey(customerId);
	}
	
	@Override
	public Result<Void> customerAdd(int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo) {
		Customer customer = EntityGenerator.newCustomer(uid, name, identity, mobile, license, regions, address, memo);
		try {
			customerMapper.insert(customer);
		} catch (DuplicateKeyException e) {
			return BtkjConsts.RESULT.CUSTOMER_IDENTITY_DUPLICATE;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Pager<Customer>> customers(CustomerSearcher searcher) {
		return Result.result(customerMapper.paging(searcher));
	}
}
