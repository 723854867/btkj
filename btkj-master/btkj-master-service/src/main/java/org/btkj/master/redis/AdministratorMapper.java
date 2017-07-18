package org.btkj.master.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.master.LuaCmd;
import org.btkj.master.mybatis.dao.AdministratorDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Administrator;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AdministratorMapper extends RedisDBAdapter<Integer, Administrator, AdministratorDao> {
	
	private String TIME_ZSET				= "zset:administrator";
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
	
	public Result<Pager<Administrator>> paging(int page, int pageSize) {
		checkLoad();
		List<byte[]> list = redis.hpaging(TIME_ZSET, redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Administrator> temp = new ArrayList<Administrator>();
		for (byte[] data : list)
			temp.add(serializer.antiConvet(data));
		return Result.result(new Pager<Administrator>(total, temp));
	}
	
	@Override
	public void flush(Administrator entity) {
		redis.hmzset(redisKey, entity, TIME_ZSET, entity.getCreated(), serializer);
	}
	
	@Override
	public void flush(Map<Integer, Administrator> entities) {
		Administrator[] array = entities.values().toArray(new Administrator[]{});
		Map<String, double[]> zsetParams = new HashMap<String, double[]>();
		double[] scores = new double[array.length];
		int idx = 0;
		for (int i = 0, len = array.length; i < len; i++)
			scores[idx++] = array[i].getCreated();
		zsetParams.put(TIME_ZSET, scores);
		redis.hmzset(redisKey, array, zsetParams, serializer);
	}
}
