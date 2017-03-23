package org.btkj.user.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.BtkjLoginInfo;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.BtkjUserService;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.hook.ApplyHook;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Service;

@Service("btkjUserService")
public class BtkjUserServiceImpl implements BtkjUserService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyHook applyHook;
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Result<Serializable> loginWithMobile(String mobile) {
		User user = userMapper.getUserByMobile(BtkjConsts.APP_ID_BAOTU, mobile);
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		else {
			Result<TokenReplaceModel> result = userMapper.tokenReplace(BtkjConsts.APP_ID_BAOTU, user.getUid(), mobile);
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
				int time = DateUtils.currentTime();
				user.setLastLoginTime(time);
				user.setUpdated(time);
				userMapper.update(user);
				
				BtkjLoginInfo loginInfo = new BtkjLoginInfo(result.attach().getToken(), user);
				int mainTid = userMapper.btkjUserMainTenant(user.getUid());
				List<TenantTips> tenantTips = employeeMapper.tenantTipsList(user.getUid(), mainTid);
				List<TenantTips> audittTenantTips = employeeMapper.auditTenantTipsList(user.getUid());
				MainTenantTips mainTenantTips = null;
				if (0 == mainTid) {
					if (!tenantTips.isEmpty()) 
						mainTenantTips = new MainTenantTips(tenantTips.get(0).getTid());
				} else 
					mainTenantTips = new MainTenantTips(mainTid);
				
				loginInfo.setTenant(mainTenantTips);
				loginInfo.setTenantList(tenantTips);
				loginInfo.setTenantAuditList(audittTenantTips);
				return Result.result(loginInfo);
			} finally {
				// 这里一定别忘记释放锁，因为在 token_replace 的 lua 脚本中我们也获取了 user 的锁资源，因此这里要释放
				userMapper.releaseUserLock(user.getUid(), result.attach().getLockId());
			}
		}
	}

	@Override
	public Result<Void> apply(String token, int tid, int chief) {
		Result<User> result = userMapper.lockUserByToken(BtkjConsts.APP_ID_BAOTU, token);
		if (!result.isSuccess()) 
			return Result.result(result.getCode(), result.getDesc(), null);
		User user = result.attach();
		String lockId = result.getDesc();
		try {
			_doApply(user, tid, chief);
		} finally {
			userMapper.releaseUserLock(user.getUid(), lockId);
		}
		return null;
	}
	
	/**
	 * 先创建用户再申请
	 */
	@Override
	public Result<Void> apply(int tid, String mobile, String name, String identity, int chief) {
		User user = userMapper.insert(BeanGenerator.newUser(BtkjConsts.APP_ID_BAOTU, mobile, identity, name));
		String lockId = userMapper.lockUser(user.getUid());
		if (null == lockId)
			return Result.result(Code.USER_STATUS_CHANGED);
		try {
			_doApply(user, tid, chief);
		} finally {
			userMapper.releaseUserLock(user.getUid(), lockId);
		}
		return null;
	}
	
	private Result<Void> _doApply(User user, int tid, int chief) {
		ApplyInfo ai = applyHook.getApplyInfo(tid, user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (null != employeeMapper.get(user.getUid(), tid))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyHook.addApplyInfo(user.getUid(), tid, chief);
		return Result.success();
	}
}
