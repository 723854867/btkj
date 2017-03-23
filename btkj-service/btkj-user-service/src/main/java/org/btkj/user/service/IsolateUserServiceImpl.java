package org.btkj.user.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.user.api.IsolateUserService;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.hook.ApplyHook;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Service;

@Service("isolateUserService")
public class IsolateUserServiceImpl implements IsolateUserService {
	
	@Resource
	private ApplyHook applyHook;
	@Resource
	private UserMapper userMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private DistributeLock distributeLock;

	@Override
	public Result<Serializable> loginWithMobile(int appId, int tid, String mobile) {
		User user = userMapper.getUserByMobile(appId, mobile);
		if (null == user) {
			ApplyInfo applyInfo = applyHook.getApplyInfo(tid, mobile);
			if (null != applyInfo)
				return Result.result(BtkjCode.APPLY_EXIST, applyInfo);
			return Result.result(Code.USER_NOT_EXIST);
		} else {
			Result<TokenReplaceModel> result = userMapper.tokenReplace(appId, user.getUid(), mobile);
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
				
				LoginInfo loginInfo = new LoginInfo(result.attach().getToken(), user);
				return Result.result(loginInfo);
			} finally {
				// 这里一定别忘记释放锁，因为在 token_replace 的 lua 脚本中我们也获取了 user 的锁资源，因此这里要释放
				distributeLock.unLock(RedisKeyGenerator.userLockKey(user.getUid()), result.attach().getLockId());
			}
		}
	}
	
	@Override
	public Result<Void> apply(int appId, int tid, String mobile, String name, String identity, int chief) {
		ApplyInfo ai = applyHook.getApplyInfo(tid, mobile);
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		// 独立 app 的用户存在就代表已经是雇员了
		User user = userMapper.getUserByMobile(appId, mobile);
		if (null != user)
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyHook.addApplyInfo(mobile, tid, name, identity, chief);
		return Result.success();
	}
}
