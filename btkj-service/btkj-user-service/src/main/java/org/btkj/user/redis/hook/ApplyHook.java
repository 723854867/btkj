package org.btkj.user.redis.hook;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.UserLuaCmd;
import org.rapid.data.storage.redis.Redis;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
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
		redis.invokeLua(UserLuaCmd.APPLY_ADD, 2, 
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tenantApplyListKey(tid)),
				SerializeUtil.RedisUtil.encode(mobile), 
				SerializeUtil.RedisUtil.encode(DateUtils.currentTime()),
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
		redis.invokeLua(UserLuaCmd.APPLY_ADD, 3,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tenantApplyListKey(tid)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.btkjApplyKey(uid)),
				SerializeUtil.RedisUtil.encode(uid),
				SerializeUtil.RedisUtil.encode(DateUtils.currentTime()),
				SerializeUtil.ProtostuffUtil.serial(ai),
				SerializeUtil.RedisUtil.encode(tid));
	}
	
	/**
	 * 分页获取申请信息
	 * 
	 * @param pager
	 * @param tid
	 */
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.tenantApplyListKey(tid)), 
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid)), 
				SerializeUtil.RedisUtil.encode(page), 
				SerializeUtil.RedisUtil.encode(pageSize),
				SerializeUtil.RedisUtil.encode(RedisConsts.CMD_ZREVRANGE));
		if (null == list)
			return null;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<ApplyInfo> applies = new ArrayList<ApplyInfo>();
		for (byte[] buffer : list)
			applies.add(SerializeUtil.ProtostuffUtil.deserial(buffer, ApplyInfo.class));
		return Result.result(new Pager<ApplyInfo>(total, applies));
	}
	
	/**
	 * 获取并且删除申请信息
	 * 
	 * @param tid
	 * @param applyKey
	 * @return
	 */
	public ApplyInfo getAndDel(int tid, String applyKey) {
		byte[] buffer = redis.invokeLua(UserLuaCmd.APPLY_GET_AND_DEL, 
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.applyDataKey(tid),
						RedisKeyGenerator.tenantApplyListKey(tid),
						RedisKeyGenerator.btkjApplyKey(applyKey),
						applyKey, tid));
		return null == buffer ? null : SerializeUtil.ProtostuffUtil.deserial(buffer, ApplyInfo.class);
	}
}
