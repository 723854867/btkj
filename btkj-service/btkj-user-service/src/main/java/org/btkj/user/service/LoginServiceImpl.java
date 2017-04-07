package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.LoginService;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.hook.ApplyHook;
import org.btkj.user.redis.mapper.EmployeeMapper;
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
	private EmployeeMapper employeeMapper;

	@Override
	public Result<LoginInfo> appLogin(App app, Tenant tenant, String mobile) {
		User user = userMapper.getUserByMobile(app.getId(), mobile);
		boolean btkj = BtkjUtil.isBaoTuApp(app);
		if (null == user) {
			if (btkj)					// 保途 app 用户不存在直接返回用户不存在
				return Result.result(Code.USER_NOT_EXIST);
			// 独立 app 需要判断是否已经有申请在审核
			ApplyInfo applyInfo = applyHook.getApplyInfo(tenant.getTid(), mobile);
			if (null != applyInfo)
				return Result.result(BtkjCode.APPLY_EXIST, new ApplyInfo.ApplyChecker(applyInfo));
			return Result.result(Code.USER_NOT_EXIST);
		} else 
			return _doLogin(ClientType.APP, app, user, mobile);
	}

	@Override
	public Result<LoginInfo> pcLogin(App app, Tenant tenant, String mobile, String pwd) {
		return _browserLogin(ClientType.PC, app, tenant, mobile, pwd);
	}

	@Override
	public Result<LoginInfo> managerLogin(App app, Tenant tenant, String mobile, String pwd) {
		return _browserLogin(ClientType.MANAGER, app, tenant, mobile, pwd);
	}
	
	/**
	 * 浏览器登录，主要是分为 pc 端和管理后台
	 * 
	 * @param ct
	 * @param app
	 * @param tenant
	 * @param mobile
	 * @param pwd
	 * @return
	 */
	private Result<LoginInfo> _browserLogin(ClientType ct, App app, Tenant tenant, String mobile, String pwd) {
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
		return _doLogin(ct, app, user, mobile);
	}
	
	private Result<LoginInfo> _doLogin(ClientType ct, App app, User user, String mobile) {
		Result<TokenReplaceModel> result = userMapper.tokenReplace(ct, app.getId(), user.getUid(), mobile);
		if (result.getCode() != 0)
			return Result.result(Code.USER_STATUS_CHANGED);
		try {
			user = userMapper.getByKey(user.getUid());		
			if (!user.getMobile().equals(mobile))
				return Result.result(Code.USER_STATUS_CHANGED);
			
			int time = DateUtils.currentTime();
			switch (ct) {
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
	public Result<?> apply(String token, Tenant tenant, User chief) {
		Result<User> result = userMapper.lockUserByToken(ClientType.APP, BtkjConsts.APP_ID_BAOTU, token);
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
	public Result<?> apply(App app, Tenant tenant, String mobile, String name, String identity, User chief) {
		if (BtkjUtil.isBaoTuApp(app)) {
			User user = userMapper.insert(BeanGenerator.newUser(app.getId(), mobile, identity, name));
			Result<TokenReplaceModel> result = userMapper.tokenReplace(ClientType.APP, app.getId(), user.getUid(), mobile);
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
		} else {
			ApplyInfo ai = applyHook.getApplyInfo(tenant.getTid(), mobile);
			if (null != ai)
				return Result.result(BtkjCode.APPLY_EXIST);
			// 独立 app 的用户存在就代表已经是雇员了
			User user = userMapper.getUserByMobile(app.getId(), mobile);
			if (null != user)
				return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
			applyHook.addApplyInfo(mobile, tenant.getTid(), name, identity, chief.getUid());
			return Result.success();
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
