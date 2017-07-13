package org.btkj.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.provider.AreaSQLProvider;
import org.btkj.vehicle.pojo.entity.Area;
import org.rapid.data.storage.mapper.DBMapper;

public interface AreaDao extends DBMapper<Integer, Area> {

	@Override
	@SelectProvider(type = AreaSQLProvider.class, method = "getByKey")
	Area getByKey(Integer key);
}
