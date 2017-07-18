package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.vo.ApplyInfo;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.rapid.data.storage.mapper.RedisMapper;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
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
		redis.hzset(redisKey, model.key(), serializer.convert(model), DateUtil.currentTime(), _tenantListKey(model.getTid()), _userListKey(model.getUid()));
	}
	
	/**
	 * 租户分页获取申请信息
	 * 
	 * @param pager
	 * @param tid
	 */
	public Pager<ApplyPagingInfo> paging(int tid, int page, int pageSize) {
		List<byte[]> list = redis.hpaging(_tenantListKey(tid), redisKey, page, pageSize,RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return Pager.EMPLTY;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<ApplyPagingInfo> applies = new ArrayList<ApplyPagingInfo>();
		for (byte[] data : list) 
			applies.add(new ApplyPagingInfo(serializer.antiConvet(data)));
		return new Pager<ApplyPagingInfo>(total, applies);
	}
	
	/**
	 * 用户申请的代理公司的id列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> applyListTids(int uid) {
		List<byte[]> list = redis.hzget(_userListKey(uid), redisKey, 0, -1);
		if (CollectionUtil.isEmpty(list))
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
