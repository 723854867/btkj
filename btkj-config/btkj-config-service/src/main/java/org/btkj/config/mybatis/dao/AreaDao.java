package org.btkj.config.mybatis.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.AreaSQLProvider;
import org.btkj.config.pojo.entity.Area;
import org.rapid.data.storage.mapper.DBMapper;

public interface AreaDao extends DBMapper<Integer, Area> {
	
	@Override
	@InsertProvider(type = AreaSQLProvider.class, method = "insert")
	void insert(Area model);
		
	@Override
	@SelectProvider(type = AreaSQLProvider.class, method = "getByKey")
	Area getByKey(Integer key);
	
	@Override
	@UpdateProvider(type = AreaSQLProvider.class, method = "update")
	void update(Area model);
}
