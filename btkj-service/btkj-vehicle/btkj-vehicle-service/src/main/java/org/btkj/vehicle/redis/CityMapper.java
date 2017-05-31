package org.btkj.vehicle.redis;

import org.btkj.vehicle.mybatis.Tables;
import org.btkj.vehicle.mybatis.dao.CityDao;
import org.btkj.vehicle.mybatis.entity.City;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class CityMapper extends RedisProtostuffDBMapper<Integer, City, CityDao> {

	public CityMapper() {
		super(Tables.CITY, "hash:db:city");
	}
}
