package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.user.mybatis.dao.NonAutoBindDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class NonAutoBindMapper extends RedisDBAdapter<Integer, NonAutoBind, NonAutoBindDao> {
	
	private static final String LIST					= "set:non_auto_bind:list:{0}";			// 租户非车险列表
	private static final String LIST_CONTROLLER			= "non_auth_bind:{0}";

	public NonAutoBindMapper() {
		super(new ByteProtostuffSerializer<NonAutoBind>(), "hash:db:non_auto_bind");
	}
	
	/**
	 * 获取指定的非车险列表
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	public List<NonAutoBind> getByTid(int tid) { 
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _listKey(tid), redisKey, _listControllerKey(tid));
		List<NonAutoBind> binds = null;
		if (null == list) {
			binds = dao.getByTid(tid);
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _listKey(tid), _listControllerKey(tid), binds, serializer);
		} else {
			binds = new ArrayList<NonAutoBind>(list.size());
			for (byte[] data : list)
				binds.add(serializer.antiConvet(data));
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
