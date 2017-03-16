package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.UserRelation;
import org.btkj.user.persistence.dao.UserRelationDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.mapper.O2MMapper;
import org.rapid.util.common.key.Pair;

public class UserRelationMapper extends O2MMapper<Pair<Integer, Integer>, UserRelation, UserRelationDao> {

	public UserRelationMapper() {
		super(BtkjTables.USER_RELATION);
	}
	
	@Override
	protected String redisKey(Pair<Integer, Integer> key) {
		return RedisKeyGenerator.userRelationKey(key.toString());
	}
	
	/**
	 * 将请求先放入内存中
	 * 
	 * @param relation
	 */
	public void tenantJoinApply(UserRelation relation) {
		
	}
}
