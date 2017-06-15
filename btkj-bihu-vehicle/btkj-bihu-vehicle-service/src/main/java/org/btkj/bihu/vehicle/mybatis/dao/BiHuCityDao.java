package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuCity;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuCitySQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface BiHuCityDao extends DBMapper<Integer, BiHuCity> {

	@Override
	@SelectProvider(type = BiHuCitySQLProvider.class, method = "getByKey")
	BiHuCity getByKey(Integer code);
}
