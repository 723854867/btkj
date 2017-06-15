package org.btkj.vehicle.redis;

import org.btkj.vehicle.mybatis.dao.CityDao;
import org.btkj.vehicle.mybatis.entity.City;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class CityMapper extends RedisDBAdapter<Integer, City, CityDao> {

	public CityMapper() {
		super(new ByteProtostuffSerializer<City>(), "hash:db:city");
	}
}
