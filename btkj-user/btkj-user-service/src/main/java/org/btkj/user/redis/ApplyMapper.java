package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.UserLuaCmd;
import org.rapid.data.storage.mapper.RedisProtostuffMemoryMapper;
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
public class ApplyMapper extends RedisProtostuffMemoryMapper<String, ApplyInfo> {
	
	private static final String DATA_KEY				= "hash:memory:apply";				// 每个 租户的所有申请信息
	private static final String USER_APPLY_LIST			= "set:user:{0}:apply:list";		// 用户的申请列表
	private static final String TENANT_APPLY_LIST		= "set:tenant:{0}:apply:list";		// 代理公司的申请列表
	
	public ApplyMapper() {
		super(DATA_KEY);
	}

	public ApplyInfo getByTidAndUid(int tid, int uid) {
		return getByKey(tid + "-" + uid);
	}
	
	@Override
	public ApplyInfo insert(ApplyInfo model) {
		redis.invokeLua(UserLuaCmd.APPLY_FLUSH,
				SerializeUtil.RedisUtil.encode(
						DATA_KEY,
						tenantApplyListKey(model.getTid()),
						userApplyListKey(model.getUid()),
						model.key(),
						DateUtils.currentTime(),
						serial(model)));
		return model;
	}
	
	/**
	 * 租户分页获取申请信息
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(tenantApplyListKey(tid)), 
				SerializeUtil.RedisUtil.encode(DATA_KEY), 
				SerializeUtil.RedisUtil.encode(page), 
				SerializeUtil.RedisUtil.encode(pageSize),
				SerializeUtil.RedisUtil.encode(RedisConsts.OPTION_ZREVRANGE));
		if (null == list)
			return Result.result(Pager.EMPLTY);
		int total = Integer.valueOf(new String(list.remove(0)));
		List<ApplyInfo> applies = new ArrayList<ApplyInfo>();
		for (byte[] data : list)
			applies.add(deserial(data));
		return Result.result(new Pager<ApplyInfo>(total, applies));
	}
	
	/**
	 * 用户申请的代理公司的id列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> applyListTids(User user) {
		List<byte[]> list = redis.hmgetByZsetKeys(
				SerializeUtil.RedisUtil.encode(userApplyListKey(user.getUid())), 
				SerializeUtil.RedisUtil.encode(DATA_KEY), 0, -1);
		List<Integer> set = new ArrayList<Integer>();
		for (byte[] buffer : list) 
			set.add(deserial(buffer).getTid());
		return set;
	}
	
	/**
	 * 获取并且删除申请信息
	 * 
	 * @param tid
	 * @param applyKey
	 * @return
	 */
	public ApplyInfo getAndDel(int tid, int uid) {
		byte[] data = redis.invokeLua(UserLuaCmd.APPLY_GET_AND_DEL, 
				SerializeUtil.RedisUtil.encode(
						DATA_KEY,
						tenantApplyListKey(tid),
						userApplyListKey(uid),
						tid + "-" + uid));
		return null == data ? null : deserial(data);
	}
	
	public static final String userApplyListKey(int uid) { 
		return MessageFormat.format(USER_APPLY_LIST, String.valueOf(uid));
	}
	
	public static final String tenantApplyListKey(int tid) { 
		return MessageFormat.format(TENANT_APPLY_LIST, String.valueOf(tid));
	}
}
