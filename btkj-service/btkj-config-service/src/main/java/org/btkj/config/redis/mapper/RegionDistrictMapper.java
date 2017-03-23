package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.RegionDistrictDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionDistrict;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class RegionDistrictMapper extends O2OMapper<Integer, RegionDistrict, byte[], RegionDistrictDao> {
	
	public RegionDistrictMapper() {
		super(BtkjTables.REGION_DISTRICT, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.regionDistrictDataKey()));
	}
}
