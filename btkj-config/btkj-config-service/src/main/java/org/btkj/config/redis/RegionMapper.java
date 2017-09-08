package org.btkj.config.redis;

import org.btkj.config.mybatis.dao.RegionDao;
import org.btkj.pojo.entity.config.Region;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class RegionMapper extends RedisDBAdapter<Integer, Region, RegionDao> {
	
	public RegionMapper() {
		super(new ByteProtostuffSerializer<Region>(), "hash:db:region");
	}
}
