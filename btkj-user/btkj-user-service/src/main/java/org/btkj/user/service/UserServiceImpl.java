package org.btkj.user.service;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.user.User.Mod;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserHolder;
import org.btkj.pojo.param.user.CustomerEditParam;
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
	public User user(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public Map<Integer, User> users(Collection<Integer> uids) {
		return userMapper.getByKeys(uids);
	}
	
	@Override
	public UserHolder userByToken(Client client, String token) {
		User user = null;
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
		User user = null;
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
			Result<User> result = userMapper.userLockByToken(client, token);
			if (!result.isSuccess())
				return Result.result(result.getCode(), result.getDesc(), null);
			user = result.attach();
			lockId = result.getDesc();
			break;
		}
		return Result.result(Code.OK.id(), lockId, new UserHolder(appMapper.getByKey(user.getAppId()), user));
	}
	
	@Override
	public Result<User> userLockByMobile(int appId, String mobile) {
		return userMapper.lockUserByMobile(appId, mobile);
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
	public Result<Void> customerEdit(CustomerEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			Customer customer = EntityGenerator.newCustomer(param);
			try {
				customerMapper.insert(customer);
			} catch (DuplicateKeyException e) {
				return BtkjConsts.RESULT.CUSTOMER_IDENTITY_DUPLICATE;
			}
			return Consts.RESULT.OK;
		case UPDATE:
			customer = customerMapper.getByKey(param.getId());
			if (null == customer)
				return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
			if (customer.getUid() != param.getUid())
				return Consts.RESULT.FORBID;
			EntityGenerator.updateCustomer(customer, param);
			customerMapper.update(customer);
			return Consts.RESULT.OK;
		case DELETE:
			customer = customerMapper.getByKey(param.getId());
			if (null == customer)
				return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
			if (customer.getUid() != param.getUid())
				return Consts.RESULT.FORBID;
			customerMapper.delete(customer);
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	public Result<Void> seal(int appId, int uid) {
		User user = userMapper.getByKey(uid);
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
		User user = userMapper.getByKey(uid);
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
