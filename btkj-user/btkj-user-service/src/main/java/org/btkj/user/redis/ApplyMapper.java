package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisMapper;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtils;
import org.rapid.util.lang.DateUtils;
import org.springframework.stereotype.Component;

/**
 * 申请钩子
 * 
 * @author ahab
 *
 */
@Component
public class ApplyMapper extends RedisMapper<String, ApplyInfo> {
	
	private final String USER_LIST				= "zset:user:{0}:apply:list";		// 用户的申请列表
	private final String TENANT_LIST			= "zset:tenant:{0}:apply:list";		// 代理公司的申请列表
	
	public ApplyMapper() {
		super(new ByteProtostuffSerializer<ApplyInfo>(), "hash:memory:apply");
	}

	public ApplyInfo getByTidAndUid(int tid, int uid) {
		return getByKey(tid + "-" + uid);
	}
	
	@Override
	public void insert(ApplyInfo model) {
		redis.hzset(redisKey, model.key(), serializer.convert(model), DateUtils.currentTime(), _tenantListKey(model.getTid()), _userListKey(model.getTid()));
	}
	
	/**
	 * 租户分页获取申请信息
	 * 
	 * @param pager
	 * @param tid
	 */
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		List<byte[]> list = redis.hpaging(_tenantListKey(tid), redisKey, page, pageSize,RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return Result.result(Pager.EMPLTY);
		int total = Integer.valueOf(new String(list.remove(0)));
		List<ApplyInfo> applies = new ArrayList<ApplyInfo>();
		for (byte[] data : list)
			applies.add(serializer.antiConvet(data));
		return Result.result(new Pager<ApplyInfo>(total, applies));
	}
	
	/**
	 * 用户申请的代理公司的id列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> applyListTids(User user) {
		List<byte[]> list = redis.hzget(_userListKey(user.getUid()), redisKey, 0, -1);
		if (CollectionUtils.isEmpty(list))
			return Collections.EMPTY_LIST;
		List<Integer> l = new ArrayList<Integer>(list.size());
		for (byte[] buffer : list) 
			l.add(serializer.antiConvet(buffer).getTid());
		return l;
	}
	
	/**
	 * 获取并且删除申请信息
	 * 
	 * @param tid
	 * @param applyKey
	 * @return
	 */
	public ApplyInfo getAndDel(int tid, int uid) {
		byte[] data = redis.hzgetDel(redisKey, tid + "-" + uid, _tenantListKey(tid), _userListKey(uid));
		return null == data ? null : serializer.antiConvet(data);
	}
	
	private String _userListKey(int uid) { 
		return MessageFormat.format(USER_LIST, String.valueOf(uid));
	}
	
	private String _tenantListKey(int tid) { 
		return MessageFormat.format(TENANT_LIST, String.valueOf(tid));
	}
}
