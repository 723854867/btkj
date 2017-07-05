package org.btkj.bihu.vehicle.redis;

import java.util.List;

import org.btkj.bihu.vehicle.mybatis.dao.BiHuCityDao;
import org.btkj.bihu.vehicle.pojo.entity.BiHuCity;
import org.btkj.pojo.BtkjConsts;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BiHuCityMapper extends RedisDBAdapter<Integer, BiHuCity, BiHuCityDao> {
	
	private String cacheControllerField					= "lock:bi_hu_city";

	public BiHuCityMapper() {
		super(new ByteProtostuffSerializer<BiHuCity>(), "hash:db:bi_hu_city");
	}
	
	public List<BiHuCity> getAll() {
		checkLoad(BtkjConsts.CACHE_CONTROLLER_KEY, cacheControllerField);
		return super.getAll();
	}
}
