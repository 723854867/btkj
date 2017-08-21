package org.btkj.master.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.master.LuaCmd;
import org.btkj.master.mybatis.dao.AdminDao;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.Param;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AdminMapper extends RedisDBAdapter<Integer, Admin, AdminDao> {
	
	private String TIME_ZSET				= "zset:admin";
	private String ADMIN_TOKEN				= "hash:admin:token";
	private String TOKEN_ADMIN				= "hash:token:admin"; 

	public AdminMapper() {
		super(new ByteProtostuffSerializer<Admin>(), "hash:db:admin");
	}
	
	public String tokenReplace(Admin administrator) {
		return redis.tokenReplace(ADMIN_TOKEN, TOKEN_ADMIN, administrator.getId());
	}
	
	public void tokenRemove(String token) {
		long flag = redis.tokenRemove(ADMIN_TOKEN, TOKEN_ADMIN, token);
		if (-2 == flag)
			return;
	}
	
	public Admin getByToken(String token) { 
		byte[] data = redis.invokeLua(LuaCmd.ADMIN_LOAD_BY_TOKEN, TOKEN_ADMIN, redisKey, token);
		return null == data ? null : serializer.antiConvet(data);
	}
	
	public Result<Pager<Admin>> admins(Param param) {
		checkLoad();
		List<byte[]> list = redis.hpaging(TIME_ZSET, redisKey, param.getPage(), param.getPageSize(), RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Admin> temp = new ArrayList<Admin>();
		for (byte[] data : list)
			temp.add(serializer.antiConvet(data));
		return Result.result(new Pager<Admin>(total, temp));
	}
	
	@Override
	public void flush(Admin entity) {
		redis.hmzset(redisKey, entity, TIME_ZSET, entity.getCreated(), serializer);
	}
	
	@Override
	public void flush(Map<Integer, Admin> entities) {
		Admin[] array = entities.values().toArray(new Admin[]{});
		Map<String, double[]> zsetParams = new HashMap<String, double[]>();
		double[] scores = new double[array.length];
		int idx = 0;
		for (int i = 0, len = array.length; i < len; i++)
			scores[idx++] = array[i].getCreated();
		zsetParams.put(TIME_ZSET, scores);
		redis.hmzset(redisKey, array, zsetParams, serializer);
	}
}
