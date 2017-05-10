package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.user.Config;
import org.btkj.user.persistence.dao.NonAutoInsuranceDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class NonAutoInsuranceMapper extends RedisProtostuffDBMapper<Integer, NonAutoInsurance, NonAutoInsuranceDao> {
	
	private static final String LIST					= "set:non_auto_insurance:list:{0}:{1}";			// 租户非车险列表
	private static final String LIST_CONTROLLER			= "non_auth_insurance:{0}:{1}";

	public NonAutoInsuranceMapper() {
		super(BtkjTables.NON_AUTO_INSURANCE, "hash:db:non_auto_insurance");
	}
	
	/**
	 * 获取指定的非车险列表
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	public List<NonAutoInsurance> getByAppIdAndTid(int appId, int tid) { 
		List<byte[]> list = redis.protostuffCacheListLoadWithData(Config.CACHE_CONTROLLER, _listKey(appId, tid), redisKey, _listControllerKey(appId, tid));
		List<NonAutoInsurance> insurances = null;
		if (null == list) {
			insurances = dao.selectByAppIdAndTid(appId, tid);
			redis.protostuffCacheListFlush(Config.CACHE_CONTROLLER, redisKey, _listKey(appId, tid), _listControllerKey(appId, tid), insurances);
		} else {
			insurances = new ArrayList<NonAutoInsurance>(list.size());
			for (byte[] data : list)
				insurances.add(deserial(data));
		}
		return insurances;
	}
	
	public String _listKey(int appId, int tid) { 
		return MessageFormat.format(LIST, String.valueOf(appId), String.valueOf(tid));
	}
	
	public String _listControllerKey(int appId, int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
