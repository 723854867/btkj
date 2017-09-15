package org.btkj.user.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.user.UserPO.Mod;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserHolder;
import org.btkj.pojo.model.identity.User;
import org.btkj.pojo.param.user.CustomerListParam;
import org.btkj.user.api.UserService;
import org.btkj.user.mybatis.EntityGenerator;
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
	public UserPO user(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public Map<Integer, UserPO> users(Collection<Integer> uids) {
		return userMapper.getByKeys(uids);
	}
	
	@Override
	public User user(String mobile, int appId) {
		UserPO po = userMapper.getUserByMobile(appId, mobile);
		return null == po ? null : new User(appMapper.getByKey(po.getAppId()), po);
	}
	
	@Override
	public UserHolder userByToken(Client client, String token) {
		UserPO user = null;
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			if (null == uid)
				return null;
			user = userMapper.getByKey(Integer.valueOf(uid));
			break;
		default:
			user = userMapper.userByToken(client, token);
			break;
		}
		return null == user ? null : new UserHolder(appMapper.getByKey(user.getAppId()), user);
	}
	
	@Override
	public Result<UserHolder> userLockByToken(Client client, String token) {
		UserPO user = null;
		String lockId = null;
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			user = null == uid ? null : userMapper.getByKey(Integer.valueOf(uid));
			lockId = null == user ? null : userMapper.lockUser(user.getUid());
			if (null == lockId)
				return Consts.RESULT.TOKEN_INVALID;
			break;
		default:
			Result<UserPO> result = userMapper.userLockByToken(client, token);
			if (!result.isSuccess())
				return Result.result(result.getCode(), result.getDesc(), null);
			user = result.attach();
			lockId = result.getDesc();
			break;
		}
		return Result.result(Code.OK.id(), lockId, new UserHolder(appMapper.getByKey(user.getAppId()), user));
	}
	
	@Override
	public Result<UserPO> userLockByMobile(int appId, String mobile) {
		return userMapper.lockUserByMobile(appId, mobile);
	}
	
	@Override
	public User getUserByToken(Client client, String token) {
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			if (null == uid)
				return null;
			UserPO user = userMapper.getByKey(Integer.valueOf(uid));
			return new User(client, appMapper.getByKey(user.getAppId()), user);
		default:
			return userMapper.getUserByToken(client, token);
		}
	}
	
	@Override
	public Result<User> lockUserByToken(Client client, String token) {
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			if (null == uid)
				return null;
			UserPO user = userMapper.getByKey(Integer.valueOf(uid));
			String lockId = userMapper.lockUser(user.getUid());
			if (null == lockId)
				return Consts.RESULT.LOCK_CONFLICT;
			return Result.result(Code.OK.id(), lockId, new User(client, appMapper.getByKey(user.getAppId()), user));
		default:
			return userMapper.lockUserByToken(client, token);
		}
	}
	
	@Override
	public UserPO getUserByEmployeeId(int employeeId) {
		EmployeePO employee = employeeMapper.getByKey(employeeId);
		return null == employee ? null : userMapper.getByKey(employee.getUid());
	}
	
	@Override
	public void releaseUserLock(String lockId, int uid) {
		userMapper.releaseUserLock(uid, lockId);
	}
	
	@Override
	public Result<Void> update(UserPO user) {
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
		Result<UserPO> result = userMapper.lockUserByMobile(appId, mobile);
		if (!result.isSuccess())
			return result;
		try {
			UserPO user = result.attach();
			user.setPwd(pwd);
			user.setUpdated(DateUtil.currentTime());
			userMapper.update(user);
			return Result.success();
		} finally {
			userMapper.releaseUserLock(result.attach().getUid(), result.getDesc());
		}
	}
	
	@Override
	public Customer getCustomerById(long customerId) {
		return customerMapper.getByKey(customerId);
	}

	@Override
	public Result<Pager<Customer>> customers(CustomerListParam param) {
		return Result.result(customerMapper.paging(param));
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
	public Result<Void> customerUpdate(long id, int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo) {
		Customer customer = customerMapper.getByKey(id);
		if (null == customer)
			return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
		if (customer.getUid() != uid)
			return Consts.RESULT.FORBID;
		EntityGenerator.updateCustomer(customer, name, identity, mobile, license, regions, address, memo);
		customerMapper.update(customer);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> customerDelete(long id, int uid) {
		Customer customer = customerMapper.getByKey(id);
		if (null == customer)
			return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
		if (customer.getUid() != uid)
			return Consts.RESULT.FORBID;
		customerMapper.delete(customer);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> seal(int appId, int uid) {
		UserPO user = userMapper.getByKey(uid);
		if (null == user)
			return Consts.RESULT.USER_NOT_EXIST;
		if (user.getAppId() != appId || BtkjUtil.isTopRole(user))
			return Consts.RESULT.FORBID;
		if (!Mod.SEAL.satisfy(user.getMod())) {
			user.setMod(user.getMod() | Mod.SEAL.mark());
			user.setUpdated(DateUtil.currentTime());
			userMapper.update(user);
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> unseal(int appId, int uid) {
		UserPO user = userMapper.getByKey(uid);
		if (null == user)
			return Consts.RESULT.USER_NOT_EXIST;
		if (user.getAppId() != appId)
			return Consts.RESULT.FORBID;
		if (Mod.SEAL.satisfy(user.getMod())) {
			user.setMod(user.getMod() & (~Mod.SEAL.mark()));
			user.setUpdated(DateUtil.currentTime());
			userMapper.update(user);
		}
		return Consts.RESULT.OK;
	}
}
