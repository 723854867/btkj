package org.btkj.user.redis;

import java.text.MessageFormat;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.Config;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.model.TokenRemoveModel;
import org.btkj.user.persistence.dao.UserDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.RapidSecurity;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;

/**
 * 用户关联的有如下几个 key： hash:db:user - 用户实体数据 hash:app:{0}:mobile:user - 手机和用户 id
 * 对应关系 hash:{0}:token:user - 用户 id 和用户 token 对应关系 hash:{0}:user:token - 用户
 * token 和用户 id 对应关系
 */
public class UserMapper extends ProtostuffDBMapper<Integer, User, UserDao> {

	private static final String USER_LOCK 					= "string:user:{0}:lock"; 			

	private static final String USER_DATA		 			= "hash:db:user"; 				
	private static final String MOBILE_USER 				= "hash:app:{0}:mobile:user"; 
	private static final String TOKEN_USER 					= "hash:{0}:token:user"; 
	private static final String USER_TOKEN 					= "hash:{0}:user:token"; 
	private static final String USER_MAIN_TENANT			= "hash:user:main:tenant";		// 用户的默认显示的代理商

	private DistributeLock distributeLock;

	public UserMapper() {
		super(BtkjTables.USER, USER_DATA);
	}
	
	public Result<User> lockUserByMobile(int appId, String mobile) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		Object data = redis.invokeLua(UserLuaCmd.USER_LOAD_BY_MOBILE_LOCK, 
				SerializeUtil.RedisUtil.encode(
						mobileUserKey(appId),
						USER_DATA, USER_LOCK,
						lockId, Config.getUserLockExpire()));
		if (data instanceof byte[]) 
			return Result.result(Code.OK, lockId, deserial((byte[]) data));
		if ((long) data == -2) {
			User user = dao.selectByMobile(appId, mobile);
			if (null == user)
				return Result.result(Code.USER_NOT_EXIST);
			flush(user);
			lockId = lockUser(user.getUid());
			if (null != lockId)
				return Result.result(Code.OK, lockId, user);
		}
		return Result.result(Code.USER_STATUS_CHANGED);
	}
	
	/**
	 * 获取用户锁
	 * 
	 * @param uid
	 * @return
	 */
	public String lockUser(int uid) {
		return distributeLock.tryLock(userLockKey(uid));
	}

	/**
	 * 通过 token 获取用户并且同时获取用户的资
	 * 
	 * @param token
	 * @return
	 */
	public Result<User> lockUserByToken(Client client, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		Object data = redis.invokeLua(UserLuaCmd.USER_LOAD_BY_TOKEN_LOCK, 
				SerializeUtil.RedisUtil.encode(
						tokenUserKey(client), 
						USER_DATA, 
						token, 
						USER_LOCK, 
						lockId, 
						Config.getUserLockExpire()));
		if (data instanceof byte[])
			return Result.result(Code.OK.id(), lockId, deserial((byte[]) data));
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
		distributeLock.unLock(userLockKey(uid), lockId);
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
				SerializeUtil.RedisUtil.encode(
						tokenUserKey(client), 
						USER_DATA, 
						token));
		return null == data ? null : deserial(data);
	}

	public String tokenReplace(Client client, int uid, String mobile) {
		String token = RapidSecurity.encodeToken(mobile);
		redis.invokeLua(UserLuaCmd.TOKEN_REPLACE, 
				userTokenKey(client),
				tokenUserKey(client), 
				String.valueOf(uid), 
				token);
		return token;
	}
	
	public Result<TokenRemoveModel> tokenRemove(Client client, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		long flag = redis.invokeLua(UserLuaCmd.TOKEN_REMOVE,
				tokenUserKey(client), userTokenKey(client),
				token, USER_LOCK, lockId, String.valueOf(Config.getUserLockExpire()));
		if (-1 == flag)
			return Result.result(Code.USER_STATUS_CHANGED);
		if (-2 == flag)
			return Result.result(Code.TOKEN_INVALID);
		return Result.result(new TokenRemoveModel((int) flag, lockId));
	}
	
	@Override
	protected void flush(User entity) {
		redis.invokeLua(UserLuaCmd.USER_FLUSH,
					SerializeUtil.RedisUtil.encode(
						redisKey,
						mobileUserKey(entity.getAppId()),
						entity.getMobile(),
						entity.getUid(),
						serial(entity)));
	}

	public int mainTenant(int uid) {
		String val = redis.hget(USER_MAIN_TENANT, String.valueOf(uid));
		return null == val ? 0 : Integer.valueOf(val);
	}

	public void setDistributeLock(DistributeLock distributeLock) {
		this.distributeLock = distributeLock;
	}

	public static final String userLockKey(int uid) {
		return MessageFormat.format(USER_LOCK, String.valueOf(uid));
	}

	public static final String userTokenKey(Client client) {
		return MessageFormat.format(USER_TOKEN, String.valueOf(client.name().toLowerCase()));
	}

	public static final String tokenUserKey(Client client) {
		return MessageFormat.format(TOKEN_USER, String.valueOf(client.name().toLowerCase()));
	}

	public static final String mobileUserKey(int appId) {
		return MessageFormat.format(MOBILE_USER, String.valueOf(appId));
	}
}
