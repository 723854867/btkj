package org.btkj.user;

import javax.annotation.Resource;

import org.btkj.pojo.entity.User;
import org.btkj.user.redis.mapper.UserMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 代表所有的操作用户
 * 
 * @author ahab
 */
@Component("role")
@Scope("prototype")
public class Role {
	
	private User user;
	@Resource
	private UserMapper userMapper;
	
	public void initWithUid(int uid) {
		System.out.println(userMapper);
	}
	
	public void initWithMobile(int appId, String mobile) { 
		
	}
	
	public void initWithToken(int appId, String token) {
		
	}
}
