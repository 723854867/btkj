package org.btkj.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.entity.City;
import org.btkj.vehicle.mybatis.provider.CitySQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface CityDao extends DBMapper<Integer, City> {

	@Override
	@SelectProvider(type = CitySQLProvider.class, method = "getByKey")
	City getByKey(Integer key);
}
