package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.UserListInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.UserModel;
import org.btkj.pojo.submit.UserSearcher;
import org.btkj.user.api.UserService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Result<Pager<UserListInfo>> userList(UserSearcher searcher) {
		return userMapper.userList(searcher);
	}
	
	@Override
	public User getUser(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public User getUser(String mobile, int appId) {
		return userMapper.getUserByMobile(appId, mobile);
	}
	
	@Override
	public UserModel getUserByToken(Client client, String token) {
		return userMapper.getUserByToken(client, token);
	}
	
	@Override
	public Result<UserModel> lockUserByToken(Client client, String token) {
		return userMapper.lockUserByToken(client, token);
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
	public Result<?> update(User user) {
		user.setUpdated(DateUtils.currentTime());
		try {
			userMapper.update(user);
		} catch (DuplicateKeyException e) {
			return Result.result(Code.IDENTITY_ALREADY_EXIST);
		}
		return Result.success();
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
}
