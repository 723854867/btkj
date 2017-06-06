package org.btkj.config.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.RegionSQLProvider;
import org.btkj.pojo.entity.Region;
import org.rapid.data.storage.db.Dao;

public interface RegionDao extends Dao<Integer, Region> {
	
	@Override
	@InsertProvider(type = RegionSQLProvider.class, method = "insert")
	void insert(Region entity);

	@Override
	@SelectProvider(type = RegionSQLProvider.class, method = "selectAll")
	List<Region> selectAll();
	
	@Override
	@SelectProvider(type = RegionSQLProvider.class, method = "selectByKey")
	Region selectByKey(Integer key);
}
