package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.Tables;
import org.btkj.bihu.vehicle.mybatis.dao.BiHuCityDao;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuCity;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class BiHuCityMapper extends RedisProtostuffDBMapper<Integer, BiHuCity, BiHuCityDao> {

	public BiHuCityMapper() {
		super(Tables.BI_HU_CITY, "hash:db:bi_hu_city");
	}
}
