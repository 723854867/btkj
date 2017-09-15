package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.user.UserPagingInfo;
import org.btkj.pojo.info.user.UserPagingMasterInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.UsersParam;
import org.btkj.user.Config;
import org.btkj.user.LuaCmd;
import org.btkj.user.model.TokenRemoveModel;
import org.btkj.user.mybatis.dao.UserDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.DistributeLock;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;

/**
 * 用户关联的有如下几个 key： hash:db:user - 用户实体数据 hash:app:{0}:mobile:user - 手机和用户 id
 * 对应关系 hash:{0}:token:user - 用户 id 和用户 token 对应关系 hash:{0}:user:token - 用户
 * token 和用户 id 对应关系
 */
public class UserMapper extends RedisDBAdapter<Integer, User, UserDao> {

	private final String USER_LOCK 						= "string:user:{0}:lock"; 			

	private final String MOBILE_USER 					= "hash:app:{0}:mobile:user"; 
	private final String TOKEN_USER 					= "hash:token:{0}:user"; 
	private final String USER_TOKEN 					= "hash:user:{0}:token"; 
	private final String USER_MAIN_TENANT				= "hash:user:main:tenant";			// 用户的默认显示的代理商

	private AppMapper appMapper;
	private DistributeLock distributeLock;

	public UserMapper() {
		super(new ByteProtostuffSerializer<User>(), "hash:db:user");
	}
	
	public Pager<UserPagingInfo> users(UsersParam param) {
		int total = dao.count(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		List<User> users = dao.users(param);
		List<UserPagingInfo> list = new ArrayList<UserPagingInfo>();
		for (User user : users) 
			list.add(param.getClient() == Client.TENANT_MANAGER ? new UserPagingInfo(user) : new UserPagingMasterInfo(user));
		return new Pager(total, list);
	}
	
	public User getUserByMobile(int appId, String mobile) {
		byte[] data = redis.invokeLua(LuaCmd.USER_LOAD_BY_MOBILE, _mobileUserKey(appId), redisKey, mobile);
		User user = null;
		if (null == data) {
			user = dao.getByMobile(appId, mobile);
			if (null != user)
				flush(user);
		} else
			user = serializer.antiConvet(data);
		return user;
	}
	
	public Result<User> lockUserByMobile(int appId, String mobile) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		Object data = redis.invokeLua(LuaCmd.USER_LOAD_BY_MOBILE_LOCK, _mobileUserKey(appId), redisKey, mobile, USER_LOCK,
						lockId, Config.getUserLockExpire());
		if (data instanceof byte[]) 
			return Result.result(Code.OK, lockId, serializer.antiConvet((byte[]) data));
		if ((long) data == -2) {
			User user = dao.getByMobile(appId, mobile);
			if (null == user)
				return Result.result(Code.USER_NOT_EXIST);
			flush(user);
			lockId = lockUser(user.getUid());
			if (null != lockId)
				return Result.result(Code.OK, lockId, user);
		}
		return Result.result(Code.LOCK_CONFLICT);
	}
	
	/**
	 * 获取用户锁
	 * 
	 * @param uid
	 * @return
	 */
	public String lockUser(int uid) {
		return distributeLock.tryLock(_userLockKey(uid));
	}
	
	public User userByToken(Client client, String token) {
		byte[] data = redis.invokeLua(LuaCmd.USER_LOAD_BY_TOKEN, _tokenUserKey(client), redisKey, token);
		return null == data ? null : serializer.antiConvet(data);
	}
	
	public Result<User> userLockByToken(Client client, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		Object data = redis.invokeLua(LuaCmd.USER_LOAD_BY_TOKEN_LOCK, _tokenUserKey(client), redisKey, token, 
						USER_LOCK, lockId, Config.getUserLockExpire());
		if (data instanceof byte[]) {
			User user = serializer.antiConvet((byte[]) data);
			return Result.result(Code.OK.id(), lockId, user);
		}
		if ((long) data == 1)
			return Result.result(Code.TOKEN_INVALID);
		return Result.result(Code.LOCK_CONFLICT);
	}

	/**
	 * 释放用户锁
	 * 
	 * @param uid
	 * @param lockId
	 */
	public void releaseUserLock(int uid, String lockId) {
		distributeLock.unLock(_userLockKey(uid), lockId);
	}
	
	public String tokenReplace(Client client, int uid, String mobile) {
		return redis.tokenReplace(_userTokenKey(client), _tokenUserKey(client), uid);
	}
	
	public Result<TokenRemoveModel> tokenRemove(Client client, String token) {
		String lockId = AlternativeJdkIdGenerator.INSTANCE.generateId().toString();
		long flag = redis.tokenRemove(_userTokenKey(client), _tokenUserKey(client), token, USER_LOCK, lockId, Config.getUserLockExpire());
		if (-1 == flag)
			return Result.result(Code.LOCK_CONFLICT);
		if (-2 == flag)
			return Result.result(Code.TOKEN_INVALID);
		return Result.result(new TokenRemoveModel((int) flag, lockId));
	}
	
	@Override
	public void flush(User entity) {
		redis.invokeLua(LuaCmd.USER_FLUSH, redisKey, _mobileUserKey(entity.getAppId()),
						entity.getMobile(), entity.getUid(), serializer.convert(entity));
	}

	public int mainTenant(int uid) {
		byte[] val = redis.hget(USER_MAIN_TENANT, String.valueOf(uid));
		return null == val ? 0 : Integer.valueOf(String.valueOf(val));
	}
	
	public void setAppMapper(AppMapper appMapper) {
		this.appMapper = appMapper;
	}

	public void setDistributeLock(DistributeLock distributeLock) {
		this.distributeLock = distributeLock;
	}

	private String _userLockKey(int uid) {
		return MessageFormat.format(USER_LOCK, String.valueOf(uid));
	}

	private String _userTokenKey(Client client) {
		return MessageFormat.format(USER_TOKEN, String.valueOf(client.name().toLowerCase()));
	}

	private String _tokenUserKey(Client client) {
		return MessageFormat.format(TOKEN_USER, String.valueOf(client.name().toLowerCase()));
	}

	private String _mobileUserKey(int appId) {
		return MessageFormat.format(MOBILE_USER, String.valueOf(appId));
	}
}
