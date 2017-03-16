package org.btkj.user.persistence;

import javax.annotation.Resource;

import org.btkj.user.persistence.dao.AppDao;
import org.btkj.user.redis.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service("tx")
public class Tx {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private AppDao appDao;

}
