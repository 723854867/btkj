package org.btkj.config.redis.mapper;

import org.btkj.config.persistence.dao.RegionCityDao;
import org.btkj.config.redis.RedisKeyGenerator;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.RegionCity;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.util.common.SerializeUtil;

public class RegionCityMapper extends O2OMapper<Integer, RegionCity, byte[], RegionCityDao> {

	public RegionCityMapper() {
		super(BtkjTables.REGION_CITY, SerializeUtil.RedisUtil.encode(RedisKeyGenerator.regionCityDataKey()));
	}
}
