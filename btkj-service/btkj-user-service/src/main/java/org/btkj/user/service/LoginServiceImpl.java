package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.LoginService;
import org.btkj.user.model.TokenRemoveModel;
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

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private ApplyMapper applyHook;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Result<?> login(App app, String mobile) {
		Result<User> ru = userMapper.lockUserByMobile(app.getId(), mobile);
		User user = ru.attach();
		String lockId = ru.getDesc();
		// 用户不存在则创建用户
		if (ru.getCode() == Code.USER_NOT_EXIST.id()) {
			try {
				user = userMapper.insert(BeanGenerator.newUser(app.getId(), mobile));
				lockId = userMapper.lockUser(user.getUid());
				if (null == lockId)
					return Result.result(Code.USER_STATUS_CHANGED);
				ru.setCode(Code.OK.id());
			} catch (DuplicateKeyException e) {			// 如果unique冲突则说明 app-mobile 组合已经存在了，则直接再次获取
				ru = userMapper.lockUserByMobile(app.getId(), mobile);
				if (!ru.isSuccess())
					return ru;
				user = ru.attach();
				lockId = ru.getDesc();
			}
		}
		if (!ru.isSuccess())
			return ru;
		try {
			return _doLogin(Client.APP, user, mobile);
		} finally {
			userMapper.releaseUserLock(user.getUid(), lockId);
		}
	}
	
	@Override
	public Result<?> login(App app, String mobile, String pwd) {
		Result<User> ru = userMapper.lockUserByMobile(app.getId(), mobile);
		if (!ru.isSuccess()) 
			return ru;
		User user = ru.attach();
		try {
			if (null == user.getPwd())
				return Result.result(Code.PWD_NOT_RESET);
			if (!pwd.equals(user.getPwd()))
				return Result.result(Code.PWD_ERROR);
			return _doLogin(Client.PC, user, mobile);
		} finally {
			userMapper.releaseUserLock(user.getUid(), ru.getDesc());
		}
	}

	private Result<LoginInfo> _doLogin(Client client, User user, String mobile) {
		String token = userMapper.tokenReplace(client, user.getUid(), mobile);
		int time = DateUtils.currentTime();
		switch (client) {
		case PC:
			user.setPcLoginTime(time);
			break;
		default:
			user.setAppLoginTime(time);
			break;
		}
		user.setUpdated(time);
		userMapper.update(user);
		return Result.result(new LoginInfo(token, user));
	}
	
	@Override
	public Result<?> logout(Client client, String token) {
		Result<TokenRemoveModel> result = userMapper.tokenRemove(client, token);
		if (!result.isSuccess())
			return result;
		try {
			// 执行注销逻辑
			return Result.success();
		} finally {
			userMapper.releaseUserLock(result.attach().getUid(), result.attach().getLockId());
		}
	}
}
