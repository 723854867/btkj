package org.btkj.master.redis;

import org.btkj.master.LuaCmd;
import org.btkj.master.mybatis.dao.AdministratorDao;
import org.btkj.pojo.entity.Administrator;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AdministratorMapper extends RedisDBAdapter<Integer, Administrator, AdministratorDao> {
	
	private String ADMINISTRATOR_TOKEN		= "hash:administrator:token";
	private String TOKEN_ADMINISTRATOR		= "hash:token:administrator"; 

	public AdministratorMapper() {
		super(new ByteProtostuffSerializer<Administrator>(), "hash:db:administrator");
	}
	
	public String tokenReplace(Administrator administrator) {
		return redis.tokenReplace(ADMINISTRATOR_TOKEN, TOKEN_ADMINISTRATOR, administrator.getId());
	}
	
	public void tokenRemove(String token) {
		long flag = redis.tokenRemove(ADMINISTRATOR_TOKEN, TOKEN_ADMINISTRATOR, token);
		if (-2 == flag)
			return;
	}
	
	public Administrator getByToken(String token) { 
		byte[] data = redis.invokeLua(LuaCmd.ADMINISTRATOR_LOAD_BY_TOKEN, TOKEN_ADMINISTRATOR, redisKey, token);
		return null == data ? null : serializer.antiConvet(data);
	}
}
