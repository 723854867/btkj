package org.btkj.vehicle.redis;

import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.mybatis.dao.CityDao;
import org.btkj.vehicle.pojo.entity.City;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class CityMapper extends RedisDBAdapter<Integer, City, CityDao> {
	
	private final String cacheControllerField				= "lock:city";

	public CityMapper() {
		super(new ByteProtostuffSerializer<City>(), "hash:db:city");
	}
	
	@Override
	public List<City> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
}
