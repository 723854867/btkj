package org.btkj.user.redis;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.user.mybatis.dao.AppDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AppMapper extends RedisDBAdapter<Integer, AppPO, AppDao> {
	
	public AppMapper() {
		super(new ByteProtostuffSerializer<AppPO>(), "hash:db:app");
	}
}
