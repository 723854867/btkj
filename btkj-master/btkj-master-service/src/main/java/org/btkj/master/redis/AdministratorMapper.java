package org.btkj.master.redis;

import java.util.ArrayList;
import java.util.List;

import org.btkj.master.LuaCmd;
import org.btkj.master.mybatis.dao.AdministratorDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class AdministratorMapper extends RedisDBAdapter<Integer, Administrator, AdministratorDao> {
	
	private String ADMINISTRATOR_TOKEN		= "hash:administrator:token";
	private String TOKEN_ADMINISTRATOR		= "hash:token:administrator"; 
	private String LOAD_LOCK				= "lock:administrator";						
	private String TIME_BASED_ZSET			= "zset:administrator";

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
	
	public Result<Pager<Administrator>> paging(int page, int pageSize) {
		_checkLoad();
		List<byte[]> list = redis.hpaging(TIME_BASED_ZSET, redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Administrator> temp = new ArrayList<Administrator>();
		for (byte[] data : list)
			temp.add(serializer.antiConvet(data));
		return Result.result(new Pager<Administrator>(total, temp));
	}
	
	private void _checkLoad() {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, LOAD_LOCK, LOAD_LOCK))
			return;
		List<Administrator> list = dao.getAll();
		if (CollectionUtil.isEmpty(list))
			return;
		flush(list);
	} 
	
	@Override
	public void flush(Administrator model) {
		redis.hmzset(redisKey, model, TIME_BASED_ZSET, Double.valueOf(model.getCreated()), serializer);
	}
}
