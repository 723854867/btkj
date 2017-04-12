package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.Config;
import org.btkj.user.model.TokenReplaceModel;
import org.btkj.user.persistence.dao.UserDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.UserLuaCmd;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.RapidSecurity;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;

/**
 * 用户关联的有如下几个 key：
 * hash:db:user - 用户实体数据
 * hash:app:{0}:mobile:user - 手机和用户 id 对应关系
 * hash:app:{0}:user:token - 用户 id 和用户 token 对应关系
 * hash:app:{0}:token:user - 用户 token 和用户 id 对应关系
 * hash:tenant:{0}:apply - 用户 手机和业务员申请对应关系
 */
public class UserMapper extends O2OMapper<Integer, User, byte[], UserDao> {
	
	private DistributeLock distributeLock;

	public UserMapper() {
		super(BtkjTables.USER, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userDataKey()));
	}
	
	@Override
	public void init() {
		// do nothing
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
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.mobileUserKey(appId)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userDataKey()),
				SerializeUtil.RedisUtil.encode(mobile));
		if (null == data) {
			user = dao.getByAppIdAndMobile(appId, mobile);
			if (null != user) 			
				refresh(user);
		} else 
			user = serializer.antiConvet(data, User.class);
		return user;
	}
	
	/**
	 * 获取用户锁
	 * 
	 * @param uid
	 * @return
	 */
	public String lockUser(int uid) { 
		String lock = RedisKeyGenerator.userLockKey(uid);
		return distributeLock.tryLock(lock);
	}
	
	/**
	 * 通过 token 获取用户并且同时获取用户的资
	 * 
	 * @param token
	 * @return
	 */
	public Result<User> lockUserByToken(Client client, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		Object data = redis.invokeLua(UserLuaCmd.LOCK_USER_BY_TOKEN, 
				SerializeUtil.RedisUtil.encode(
						RedisKeyGenerator.tokenUserKey(client),
						RedisKeyGenerator.userDataKey(), 
						token, 
						RedisKeyGenerator.USER_LOCK, 
						lockId, 
						Config.getUserLockExpire()));
		if (data instanceof byte[])
			return Result.result(Code.OK.id(), lockId, serializer.antiConvet((byte[]) data, User.class));
		if ((long) data == 1)
			return Result.result(Code.USER_NOT_EXIST);
		return Result.result(Code.USER_STATUS_CHANGED);
	}
	
	/**
	 * 释放用户锁
	 * 
	 * @param uid
	 * @param lockId
	 */
	public void releaseUserLock(int uid, String lockId) {
		distributeLock.unLock(RedisKeyGenerator.userLockKey(uid), lockId);
	}
	
	/**
	 * 通过 token 获取用户，不会获取用户锁
	 * 
	 * @param app
	 * @param token
	 * @return
	 */
	public User getUserByToken(Client client, String token) {
		byte[] data = redis.invokeLua(UserLuaCmd.GET_USER_BY_TOKEN,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tokenUserKey(client),
						RedisKeyGenerator.userDataKey(), token));
		return null == data ? null : serializer.antiConvet(data, User.class);
	}

	public Result<TokenReplaceModel> tokenReplace(Client client, int uid, String mobile) { 
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		String token = RapidSecurity.encodeToken(mobile);
		long flag = redis.invokeLua(UserLuaCmd.TOKEN_REPLACE, 
				RedisKeyGenerator.userLockKey(uid), 
				RedisKeyGenerator.userTokenKey(client),
				RedisKeyGenerator.tokenUserKey(client),
				lockId, String.valueOf(uid), token, String.valueOf(Config.getUserLockExpire()));
		return 0 == flag ? Result.result((int)flag, new TokenReplaceModel(lockId, token)) : Result.result((int)flag);
	}
	
	@Override
	protected void refresh(User entity) {
		byte[] data = serializer.convert(entity);
		redis.invokeLua(UserLuaCmd.REFRESH_USER, 
				redisKey,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.mobileUserKey(entity.getAppId())),
				SerializeUtil.RedisUtil.encode(entity.getMobile()), 
				SerializeUtil.RedisUtil.encode(entity.getUid()), data);
	}
	
	public int mainTenant(int uid) {
		String val = redis.hget(RedisKeyGenerator.btkjUserMainTenantKey(), String.valueOf(uid));
		return null == val ? 0 : Integer.valueOf(val);
	}
	
	public void setDistributeLock(DistributeLock distributeLock) {
		this.distributeLock = distributeLock;
	}
}
