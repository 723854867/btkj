package org.btkj.config.redis;

import org.btkj.config.persistence.dao.RegionDao;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.springframework.stereotype.Component;

@Component("regionMapper")
public class RegionMapper extends ProtostuffDBMapper<Integer, Region, RegionDao> {
	
	private static final String REGION_DATA				= "hash:db:region";

	public RegionMapper() {
		super(BtkjTables.REGION, REGION_DATA);
	}
}
