package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.LoginService;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private ApplyMapper applyHook;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Result<LoginInfo> login(App app, String mobile) {
		User user = userMapper.getByMobile(app.getId(), mobile);
		if (null == user) {
			try {
				user = userMapper.insert(BeanGenerator.newUser(app.getId(), mobile));
			} catch (DuplicateKeyException e) {
				user = userMapper.getByMobile(app.getId(), mobile);
			}
		}
		return _doLogin(Client.APP, app, user, mobile);
	}

	@Override
	public Result<LoginInfo> login(Client client, App app, Tenant tenant, String mobile, String pwd) {
		User user = userMapper.getByMobile(app.getId(), mobile);
		if (null == user) 
			return Result.result(Code.USER_NOT_EXIST);
		Employee employee = employeeMapper.getByTidAndUid(tenant.getTid(), user.getUid());
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
}
