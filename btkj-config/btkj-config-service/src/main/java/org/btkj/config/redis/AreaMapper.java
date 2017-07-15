package org.btkj.config.redis;

import org.btkj.config.mybatis.dao.AreaDao;
import org.btkj.config.pojo.entity.Area;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AreaMapper extends RedisDBAdapter<Integer, Area, AreaDao> {
	
	public AreaMapper() {
		super(new ByteProtostuffSerializer<Area>(), "hash:db:area");
	}
}
