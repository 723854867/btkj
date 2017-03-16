package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.user.Config;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.persistence.dao.UserDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.UserLuaCmd;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.RapidSecurity;
import org.rapid.util.common.SerializeUtil;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;

/**
 * 用户关联的有如下几个 key：
 * hash:db:user - 用户实体数据
 * hash:app:{0}:mobile:user - 手机和用户 id 对应关系
 * hash:app:{0}:user:token - 用户 id 和用户 token 对应关系
 * hash:app:{0}:token:user - 用户 token 和用户 id 对应关系
 */
public class UserMapper extends O2OMapper<Integer, User, byte[], UserDao> {

	public UserMapper() {
		super(BtkjTables.USER, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userDataKey()));
	}
	
	@Override
	public void init() {
		// do nothing
	}
	
	/**
	 * 通过 token 获取用户并且同时获取用户的资
	 * 
	 * @param token
	 * @return
	 */
	public Result<User> lockUserByToken(App app, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		byte[] data = redis.invokeLua(UserLuaCmd.LOCK_USER_BY_TOKEN, 
				RedisKeyGenerator.tokenUserKey(app.getId()),
				RedisKeyGenerator.userDataKey(),
				token, RedisKeyGenerator.USER_LOCK, lockId,
				String.valueOf(Config.getUserLockExpire()));
		return null == data ? null : Result.result(0, lockId, serializer.antiConvet(data, User.class));
	}
	
	/**
	 * 通过 token 获取用户，不会获取用户锁
	 * 
	 * @param app
	 * @param token
	 * @return
	 */
	public User getUserByToken(App app, String token) {
		byte[] data = redis.invokeLua(UserLuaCmd.GET_USER_BY_TOKEN,
				RedisKeyGenerator.tokenUserKey(app.getId()),
				RedisKeyGenerator.userDataKey(), token);
		return null == data ? null : serializer.antiConvet(data, User.class);
	}

	/**
	 * 通过手机获取用户
	 * 
	 * @param appId
	 * @param mobile
	 * @return
	 */
	public User getUserByMobile(int appId, String mobile) { 
		User user = null;
		byte[] data = redis.invokeLua(UserLuaCmd.GET_USER_BY_MOBILE, 
				RedisKeyGenerator.mobileUserKey(appId),
				RedisKeyGenerator.userDataKey(),
				mobile);
		if (null == data) {
			user = dao.getByAppIdAndMobile(appId, mobile);
			if (null != user) 			
				refresh(user);
		} else 
			user = serializer.antiConvet(data, User.class);
		return user;
	}
	
	public Result<TokenReplaceModel> tokenReplace(int appId, int uid, String mobile) { 
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		String token = RapidSecurity.encodeToken(mobile);
		long flag = redis.invokeLua(UserLuaCmd.TOKEN_REPLACE, 
				RedisKeyGenerator.userLockKey(uid), 
				RedisKeyGenerator.userTokenKey(appId),
				RedisKeyGenerator.tokenUserKey(appId),
				lockId, String.valueOf(uid), token, String.valueOf(Config.getUserLockExpire()));
		return 0 == flag ? Result.result((int)flag, new TokenReplaceModel(lockId, token)) : Result.result((int)flag);
	}
	
	@Override
	protected void refresh(User entity) {
		byte[] data = serializer.convert(entity);
		redis.invokeLua(UserLuaCmd.REFRESH_USER, 
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.mobileUserKey(entity.getAppId())),
				redisKey, SerializeUtil.RedisUtil.encode(entity.getMobile()), 
				SerializeUtil.RedisUtil.encode(String.valueOf(entity.getUid())), data);
	}
}
