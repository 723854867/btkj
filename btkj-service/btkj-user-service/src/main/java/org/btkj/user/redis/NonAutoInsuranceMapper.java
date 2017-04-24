package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.user.Config;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.persistence.dao.NonAutoInsuranceDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class NonAutoInsuranceMapper extends ProtostuffDBMapper<Integer, NonAutoInsurance, NonAutoInsuranceDao> {
	
	private static final String DATA_KEY				= "hash:db:non_auto_insurance";
	private static final String LIST					= "set:non_auto_insurance:list:{0}:{1}";			// 租户非车险列表
	private static final String LIST_CONTROLLER			= "non_auth_insurance:{0}:{1}";

	public NonAutoInsuranceMapper() {
		super(BtkjTables.NON_AUTO_INSURANCE, DATA_KEY);
	}
	
	/**
	 * 获取指定的非车险列表
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	public List<NonAutoInsurance> getByAppIdAndTid(int appId, int tid) { 
		List<byte[]> list = redis.invokeLua(UserLuaCmd.NON_AUTO_INSURANCE_LIST, 
				SerializeUtil.RedisUtil.encode(
						Config.CACHE_CONTROLLER,
						listKey(appId, tid),
						DATA_KEY,
						listControllerKey(appId, tid)));
		List<NonAutoInsurance> insurances = null;
		if (null == list) {
			insurances = dao.selectByAppIdAndTid(appId, tid);
			byte[][] params = new byte[insurances.size() * 2 + 4][];
			int index = 0;
			params[index++] = SerializeUtil.RedisUtil.encode(Config.CACHE_CONTROLLER);
			params[index++] = SerializeUtil.RedisUtil.encode(DATA_KEY);
			params[index++] = SerializeUtil.RedisUtil.encode(listKey(appId, tid));
			params[index++] = SerializeUtil.RedisUtil.encode(listControllerKey(appId, tid));
			for (int i = 0, len = insurances.size(); i < len; i++) {
				NonAutoInsurance insurance = insurances.get(i);
				params[index++] = SerializeUtil.RedisUtil.encode(insurance.getId());
				params[index++] = serial(insurance);
			}
			redis.invokeLua(UserLuaCmd.NON_AUTO_INSURANCE_LIST_FLUSH, params);
		} else {
			insurances = new ArrayList<NonAutoInsurance>(list.size());
			for (byte[] data : list)
				insurances.add(deserial(data));
		}
		return insurances;
	}
	
	public static final String listKey(int appId, int tid) { 
		return MessageFormat.format(LIST, String.valueOf(appId), String.valueOf(tid));
	}
	
	public static final String listControllerKey(int appId, int tid) { 
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(appId), String.valueOf(tid));
	}
}
