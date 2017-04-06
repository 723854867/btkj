package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.RegionDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class RegionMapper extends O2OMapper<Integer, Region, byte[], RegionDao> {

	public RegionMapper() {
		super(BtkjTables.REGION, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.regionDataKey()));
	}
}
