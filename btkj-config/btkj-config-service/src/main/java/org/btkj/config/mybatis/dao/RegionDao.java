package org.btkj.config.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.RegionSQLProvider;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.mapper.DBMapper;

public interface RegionDao extends DBMapper<Integer, Region> {
	
	@Override
	@InsertProvider(type = RegionSQLProvider.class, method = "insert")
	void insert(Region entity);

	@SelectProvider(type = RegionSQLProvider.class, method = "getAll")
	List<Region> getAll();
	
	@Override
	@SelectProvider(type = RegionSQLProvider.class, method = "getByKey")
	Region getByKey(Integer key);
}
