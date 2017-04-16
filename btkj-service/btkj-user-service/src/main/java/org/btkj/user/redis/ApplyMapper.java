package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.UserLuaCmd;
import org.rapid.data.storage.mapper.ProtostuffMemoryMapper;
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
public class ApplyMapper extends ProtostuffMemoryMapper<String, ApplyInfo> {
	
	private static final String APPLY_DATA				= "hash:memory:apply";				// 每个 租户的所有申请信息
	private static final String USER_APPLY_LIST			= "set:user:{0}:apply:list";		// 用户的申请列表
	private static final String TENANT_APPLY_LIST		= "set:tenant:{0}:apply:list";		// 代理公司的申请列表
	
	public ApplyMapper() {
		super(APPLY_DATA);
	}

	public ApplyInfo getByTidAndUid(int tid, int uid) {
		return getByKey(tid + "-" + uid);
	}
	
	@Override
	public ApplyInfo insert(ApplyInfo model) {
		redis.invokeLua(UserLuaCmd.APPLY_FLUSH,
				SerializeUtil.RedisUtil.encode(
						APPLY_DATA,
						tenantApplyListKey(model.getTid()),
						userApplyListKey(model.getUid()),
						model.key(),
						DateUtils.currentTime(),
						serial(model)));
		return model;
	}
	
	/**
	 * 分页获取申请信息
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(tenantApplyListKey(tid)), 
				SerializeUtil.RedisUtil.encode(APPLY_DATA), 
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
	 * 获取并且删除申请信息
	 * 
	 * @param tid
	 * @param applyKey
	 * @return
	 */
	public ApplyInfo getAndDel(int tid, int uid) {
		byte[] data = redis.invokeLua(UserLuaCmd.APPLY_GET_AND_DEL, 
				SerializeUtil.RedisUtil.encode(
						APPLY_DATA,
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
