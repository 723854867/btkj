package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuCity;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuCitySQLProvider;
import org.rapid.data.storage.db.Dao;

public interface BiHuCityDao extends Dao<Integer, BiHuCity> {

	@Override
	@SelectProvider(type = BiHuCitySQLProvider.class, method = "selectByKey")
	BiHuCity selectByKey(Integer code);
}
