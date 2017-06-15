package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.mybatis.dao.BiHuCityDao;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuCity;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class BiHuCityMapper extends RedisDBAdapter<Integer, BiHuCity, BiHuCityDao> {

	public BiHuCityMapper() {
		super(new ByteProtostuffSerializer<BiHuCity>(), "hash:db:bi_hu_city");
	}
}
