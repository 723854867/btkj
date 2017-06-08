package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.user.mybatis.dao.NonAutoBindDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class NonAutoBindMapper extends RedisProtostuffDBMapper<Integer, NonAutoBind, NonAutoBindDao> {
	
	private static final String LIST					= "set:non_auto_bind:list:{0}";			// 租户非车险列表
	private static final String LIST_CONTROLLER			= "non_auth_bind:{0}";

	public NonAutoBindMapper() {
		super(BtkjTables.NON_AUTO_BIND, "hash:db:non_auto_bind");
	}
	
	/**
	 * 获取指定的非车险列表
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	public List<NonAutoBind> getByTid(int tid) { 
		List<byte[]> list = redis.protostuffCacheListLoadWithData(BtkjConsts.CACHE_CONTROLLER, _listKey(tid), redisKey, _listControllerKey(tid));
		List<NonAutoBind> binds = null;
		if (null == list) {
			binds = dao.selectByTid(tid);
			redis.protostuffCacheListFlush(BtkjConsts.CACHE_CONTROLLER, redisKey, _listKey(tid), _listControllerKey(tid), binds);
		} else {
			binds = new ArrayList<NonAutoBind>(list.size());
			for (byte[] data : list)
				binds.add(deserial(data));
		}
		return binds;
	}
	
	public String _listKey(int tid) { 
		return MessageFormat.format(LIST, String.valueOf(tid));
	}
	
	public String _listControllerKey(int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(tid));
	}
}
