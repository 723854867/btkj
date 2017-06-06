package org.btkj.config.redis;

import org.btkj.config.mybatis.dao.RegionDao;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.springframework.stereotype.Component;

@Component("regionMapper")
public class RegionMapper extends RedisProtostuffDBMapper<Integer, Region, RegionDao> {
	
	private static final String REGION_DATA				= "hash:db:region";

	public RegionMapper() {
		super(BtkjTables.REGION, REGION_DATA);
	}
}
