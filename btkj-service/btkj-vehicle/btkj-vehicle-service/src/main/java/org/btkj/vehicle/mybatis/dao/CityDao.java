package org.btkj.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.entity.City;
import org.btkj.vehicle.mybatis.provider.CitySQLProvider;
import org.rapid.data.storage.db.Dao;

public interface CityDao extends Dao<Integer, City> {

	@Override
	@SelectProvider(type = CitySQLProvider.class, method = "selectByKey")
	City selectByKey(Integer key);
}
