package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.LoginService;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.hook.ApplyHook;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.TenantMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private ApplyHook applyHook;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Result<LoginInfo> appLogin(App app, String mobile) {
		User user = userMapper.getUserByMobile(app.getId(), mobile);
		if (null == user) 
			return Result.result(Code.USER_NOT_EXIST);
		else 
			return _doLogin(Client.APP, app, user, mobile);
	}

	@Override
	public Result<LoginInfo> browserLogin(Client client, App app, Tenant tenant, String mobile, String pwd) {
		User user = userMapper.getUserByMobile(app.getId(), mobile);
		if (null == user) 
			return Result.result(Code.USER_NOT_EXIST);
		Employee employee = employeeMapper.getByUidAndTid(user.getUid(), tenant.getTid());
		if (null == employee)
			return Result.result(Code.FORBID);
		String cpwd = null == employee.getPwd() ? tenant.getPwd() : employee.getPwd();
		if (!pwd.equals(cpwd))
			return Result.result(Code.PWD_ERROR);
		if (null == employee.getPwd())
			return Result.result(Code.PWD_NOT_RESET);
		return _doLogin(client, app, user, mobile);
	}

	private Result<LoginInfo> _doLogin(Client client, App app, User user, String mobile) {
		Result<TokenReplaceModel> result = userMapper.tokenReplace(client, user.getUid(), mobile);
		if (result.getCode() != 0)
			return Result.result(Code.USER_STATUS_CHANGED);
		try {
			user = userMapper.getByKey(user.getUid());		
			if (!user.getMobile().equals(mobile))
				return Result.result(Code.USER_STATUS_CHANGED);
			
			int time = DateUtils.currentTime();
			switch (client) {
			case PC:
				user.setPcLoginTime(time);
				break;
			case MANAGER:
				user.setManagerLoginTime(time);
				break;
			default:
				user.setAppLoginTime(time);
				break;
			}
			user.setUpdated(time);
			userMapper.update(user);
			return Result.result(new LoginInfo(result.attach().getToken(), user));
		} finally {
			userMapper.releaseUserLock(user.getUid(), result.attach().getLockId());
		}
	}
	
	@Override
	public Result<?> apply(String token, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		User chief = userMapper.getByKey(employee.getUid());
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		Result<User> result = userMapper.lockUserByToken(Client.APP, token);
		if (!result.isSuccess()) 
			return result;
		User user = result.attach();
		String lockId = result.getDesc();
		try {
			return _doApply(tenant, user, chief);
		} finally {
			userMapper.releaseUserLock(user.getUid(), lockId);
		}
	}
	
	@Override
	public Result<?> apply(int appId, String mobile, String name, String identity, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		User chief = userMapper.getByKey(employee.getUid());
		if (chief.getAppId() != appId)
			return Result.result(Code.FORBID);
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		
		User user = userMapper.insert(BeanGenerator.newUser(appId, mobile, identity, name));
		Result<TokenReplaceModel> result = userMapper.tokenReplace(Client.APP, user.getUid(), mobile);
		if (result.getCode() != 0)
			return Result.result(Code.USER_STATUS_CHANGED);
		try {
			/**
			 * 用户的手机可以修改，假如在 getUserByMobile 时获取的时用户 A，执行到这里的时候用户的手机修改了，
			 * 这时我们调用 update 有可能会将修改过的手机又重置回去。用户的 uid 是唯一的因此根据 uid 来获取的用户是固定不变的
			 */
			user = userMapper.getByKey(user.getUid());		
			if (!user.getMobile().equals(mobile))
				return Result.result(Code.USER_STATUS_CHANGED);
			_doApply(tenant, user, chief);
			return Result.result(new LoginInfo(result.attach().getToken(), user));
		} finally {
			userMapper.releaseUserLock(user.getUid(), result.attach().getLockId());
		}
	}
	
	private Result<?> _doApply(Tenant tenant, User user, User chief) {
		ApplyInfo ai = applyHook.getApplyInfo(tenant.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (null != employeeMapper.getByUidAndTid(user.getUid(), tenant.getTid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyHook.addApplyInfo(user.getUid(), tenant.getTid(), chief.getUid());
		return Result.success();
	}
}
