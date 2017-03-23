package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.RegionProvinceDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionProvince;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class RegionProvinceMapper extends O2OMapper<Integer, RegionProvince, byte[], RegionProvinceDao> {
	
	public RegionProvinceMapper() {
		super(BtkjTables.REGION_PROVINCE, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.regionProvinceDataKey()));
	}
}
