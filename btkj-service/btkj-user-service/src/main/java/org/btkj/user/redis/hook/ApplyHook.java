package org.btkj.user.redis.hook;

import javax.annotation.Resource;

import org.btkj.pojo.info.ApplyInfo;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Component;

/**
 * 申请钩子
 * 
 * @author ahab
 *
 */
@Component
public class ApplyHook {
	
	@Resource
	private Redis redis;

	/**
	 * 获取申请信息：独立 app 用户
	 * 
	 * @param tid
	 * @param mobile
	 * @return
	 */
	public ApplyInfo getApplyInfo(int tid, String mobile) { 
		byte[] data = redis.hget(
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)), 
				SerializeUtil.RedisUtil.encode(mobile));
		return null == data ? null : SerializeUtil.ProtostuffUtil.deserial(data, ApplyInfo.class);
	}
	
	/**
	 * 获取申请信息：保途 app 用户
	 * 
	 * @param tid
	 * @param uid
	 * @return
	 */
	public ApplyInfo getApplyInfo(int tid, int uid) { 
		byte[] data = redis.hget(
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)), 
				SerializeUtil.RedisUtil.encode(String.valueOf(uid)));
		return null == data ? null : SerializeUtil.ProtostuffUtil.deserial(data, ApplyInfo.class);
	}
	
	/**
	 * 添加申请信息：独立 app
	 * 
	 * @param tid
	 * @param mobile
	 * @param name
	 * @param identity
	 */
	public void addApplyInfo(String mobile, int tid, String name, String identity, int chief) {
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(tid);
		ai.setChief(chief);
		ai.setName(name);
		ai.setIdentity(identity);
		ai.setTime(DateUtils.currentTime());
		redis.hset(
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)), 
				SerializeUtil.RedisUtil.encode(mobile), 
				SerializeUtil.ProtostuffUtil.serial(ai));
	}
	
	/**
	 * 添加申请信息：保途 app
	 * 
	 * @param uid
	 * @param tid
	 */
	public void addApplyInfo(int uid, int tid, int chief) {
		ApplyInfo ai = new ApplyInfo();
		ai.setUid(uid);
		ai.setTid(tid);
		ai.setChief(chief);
		ai.setTime(DateUtils.currentTime());
		redis.hset(
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)), 
				SerializeUtil.RedisUtil.encode(uid), 
				SerializeUtil.ProtostuffUtil.serial(ai));
	}
}
