package org.btkj.user.redis.mapper;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.user.persistence.dao.AppDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class AppMapper extends O2OMapper<Integer, App, byte[], AppDao> {

	public AppMapper() {
		super(BtkjTables.APP, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.appDataKey()));
	}
}
