package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.CityCode;
import org.btkj.bihu.vehicle.mybatis.provider.CityCodeSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface CityCodeDao extends Dao<Integer, CityCode> {

	@Override
	@SelectProvider(type = CityCodeSQLProvider.class, method = "selectByKey")
	CityCode selectByKey(Integer code);
}
