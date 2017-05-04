package org.btkj.master.redis;

import org.btkj.master.MasterLuaCmd;
import org.btkj.master.persistence.dao.AdministratorDao;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Administrator;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class AdministratorMapper extends ProtostuffDBMapper<Integer, Administrator, AdministratorDao> {
	
	private String ADMINISTRATOR_TOKEN		= "hash:administrator:token";
	private String TOKEN_ADMINISTRATOR		= "hash:token:administrator"; 

	public AdministratorMapper() {
		super(BtkjTables.ADMINISTRATOR, "hash:db:administrator");
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
		byte[] data = redis.invokeLua(MasterLuaCmd.ADMINISTRATOR_LOAD_BY_TOKEN, 
				SerializeUtil.RedisUtil.encode(TOKEN_ADMINISTRATOR, redisKey, token));
		return null == data ? null : deserial(data);
	}
}
