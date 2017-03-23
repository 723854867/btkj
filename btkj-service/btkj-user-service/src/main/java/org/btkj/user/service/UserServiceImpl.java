package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.user.api.UserService;
import org.btkj.user.redis.mapper.TenantMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private DistributeLock distributeLock;
	
	@Override
	public User getUserByUid(int uid) {
		return userMapper.getByKey(uid);
	}
	
//	@Override
//	public Result<User> lockUserByToken(App app, String token) {
//		return userMapper.lockUserByToken(app, token);
//	}
//	
//	/**
//	 * 这里仅仅做简单的验证，没有做并发控制，真正的并发控制在同意申请的业务中处理
//	 */
//	@Override
//	public void touristJoinTenantApply(InviterModel inviter, String token, String name, String identity) {
//		
//	}
//	
//	/**
//	 * 这里仅仅做简单的验证，没有做并发控制，真正的并发控制在同意申请的业务中处理
//	 */
//	@Override
//	public void memberJoinTenantApply(InviterModel inviter, String mobile) {
//		
//		
//	}
//	
//	@Override
//	public boolean recruitAuth(int appId, int tid, String mobile) {
//		return false;
//	}
//	
////	Result<User> result = userMapper.lockUserByToken(inviter.getApp(), token);
////	if (null == result)
////		return;
////	User user = result.attach();
////	try {
////		Pair<Integer, Integer> key = new Pair<Integer, Integer>(inviter.getTenant().getTid(), 
////				null == inviter.getUser() ? 0 : inviter.getUser().getUid());
////		UserRelation relation = userRelationMapper.getByKey(key);
////		if (null == relation)
////			return;
////		
////	} finally {
////		distributeLock.unLock(RedisKeyGenerator.userLockKey(user.getUid()), result.getDesc());
////	}
}
