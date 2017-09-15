package org.btkj.user.redis;

import org.btkj.pojo.entity.user.App;
import org.btkj.user.mybatis.dao.AppDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AppMapper extends RedisDBAdapter<Integer, App, AppDao> {
	
	public AppMapper() {
		super(new ByteProtostuffSerializer<App>(), "hash:db:app");
	}
}
