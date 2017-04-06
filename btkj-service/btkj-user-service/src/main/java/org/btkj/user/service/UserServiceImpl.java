package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.ClientType;
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
	public User getUser(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public User getUserByToken(ClientType ct, App app, String token) {
		return userMapper.getUserByToken(ct, app, token);
	}
	
	@Override
	public Result<User> lockUserByToken(ClientType ct, App app, String token) {
		return userMapper.lockUserByToken(ct, app.getId(), token);
	}
}
