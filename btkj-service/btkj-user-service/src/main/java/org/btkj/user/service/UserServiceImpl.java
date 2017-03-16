package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.InviterModel;
import org.btkj.pojo.model.UserLoginModel;
import org.btkj.user.api.UserService;
import org.btkj.user.model.CreateMarker;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.mapper.UserMapper;
import org.btkj.user.redis.mapper.UserRelationMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private DistributeLock distributeLock;
	@Resource
	private UserRelationMapper userRelationMapper;
	
	@Override
	public User getUserByUid(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public Result<User> lockUserByToken(App app, String token) {
		return userMapper.lockUserByToken(app, token);
	}
	
	@Override
	public Result<UserLoginModel> loginWithMobile(int appId, String mobile) {
		User user = userMapper.getUserByMobile(appId, mobile);
		if (null == user) {				// 生成创建验证码校验临时 token，在该 token 失效之前，可以创建一个新的用户，否则需要重新获取验证码走登录逻辑
			String token = userMapper.recordCreateMark(appId, mobile);
			return Result.result(-2, token, null);
		} else {
			Result<TokenReplaceModel> result = userMapper.tokenReplace(appId, user.getUid(), mobile);
			if (result.getCode() != 0)
				return Result.result(-1);
			try {
				/**
				 * 用户的手机可以修改，假如在 getUserByMobile 时获取的时用户 A，执行到这里的时候用户的手机修改了，
				 * 这时我们调用 update 有可能会将修改过的手机又重置回去。用户的 uid 是唯一的因此根据 uid 来获取的用户是固定不变的
				 */
				user = userMapper.getByKey(user.getUid());			
				int time = DateUtils.currentTime();
				user.setLastLoginTime(time);
				user.setUpdated(time);
				userMapper.update(user);
				return Result.result(0, new UserLoginModel(user, result.attach().getToken()));
			} finally {
				// 这里一定别忘记释放锁，因为在 token_replace 的 lua 脚本中我们也获取了 user 的锁资源，因此这里要释放
				distributeLock.unLock(RedisKeyGenerator.userLockKey(user.getUid()), result.attach().getLockId());
			}
		}
	}
	
	/**
	 * 这里仅仅做简单的验证，没有做并发控制，真正的并发控制在同意申请的业务中处理
	 */
	@Override
	public void touristJoinTenantApply(InviterModel inviter, String token, String name, String identity) {
		CreateMarker cm = userMapper.eraseCreateMark(token);
		if (null == cm)
			return;
		
	}
	
	/**
	 * 这里仅仅做简单的验证，没有做并发控制，真正的并发控制在同意申请的业务中处理
	 */
	@Override
	public void memberJoinTenantApply(InviterModel inviter, String mobile) {
		
		
	}
//	Result<User> result = userMapper.lockUserByToken(inviter.getApp(), token);
//	if (null == result)
//		return;
//	User user = result.attach();
//	try {
//		Pair<Integer, Integer> key = new Pair<Integer, Integer>(inviter.getTenant().getTid(), 
//				null == inviter.getUser() ? 0 : inviter.getUser().getUid());
//		UserRelation relation = userRelationMapper.getByKey(key);
//		if (null == relation)
//			return;
//		
//	} finally {
//		distributeLock.unLock(RedisKeyGenerator.userLockKey(user.getUid()), result.getDesc());
//	}
}
