package org.btkj.bihu.vehicle.redis;

import org.btkj.bihu.vehicle.persistence.Tables;
import org.btkj.bihu.vehicle.persistence.dao.CityCodeDao;
import org.btkj.bihu.vehicle.persistence.entity.CityCode;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;

public class CityCodeMapper extends RedisProtostuffDBMapper<Integer, CityCode, CityCodeDao> {

	public CityCodeMapper() {
		super(Tables.CITY_CODE, "hash:db:city_code");
	}
}
