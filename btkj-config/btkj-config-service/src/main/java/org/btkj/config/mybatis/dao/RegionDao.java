package org.btkj.config.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.RegionSQLProvider;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.mapper.DBMapper;

public interface RegionDao extends DBMapper<Integer, Region> {
	
	@Override
	@InsertProvider(type = RegionSQLProvider.class, method = "insert")
	void insert(Region entity);

	@MapKey("id")
	@SelectProvider(type = RegionSQLProvider.class, method = "getAll")
	Map<Integer, Region> getAll();
	
	@Override
	@SelectProvider(type = RegionSQLProvider.class, method = "getByKey")
	Region getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = RegionSQLProvider.class, method = "getByKeys")
	Map<Integer, Region> getByKeys(@Param("keys") Collection<Integer> keys);
}
