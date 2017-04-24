package org.btkj.user.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Community;
import org.btkj.user.persistence.dao.CommunityDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;

public class CommunityMapper extends ProtostuffDBMapper<Integer, Community, CommunityDao> {
	
	private static final String DATA_KEY					= "hash:db:community";

	public CommunityMapper() {
		super(BtkjTables.COMMUNITY, DATA_KEY);
	}
}
