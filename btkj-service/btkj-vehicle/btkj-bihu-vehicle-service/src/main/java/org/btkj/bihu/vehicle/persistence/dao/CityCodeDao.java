package org.btkj.bihu.vehicle.persistence.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.persistence.entity.CityCode;
import org.btkj.bihu.vehicle.persistence.provider.CityCodeSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface CityCodeDao extends Dao<Integer, CityCode> {

	@Override
	@SelectProvider(type = CityCodeSQLProvider.class, method = "selectByKey")
	CityCode selectByKey(Integer code);
}
