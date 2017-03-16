package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionCitySQLProvider;
import org.btkj.pojo.entity.RegionCity;
import org.rapid.data.storage.db.Dao;

public interface RegionCityDao extends Dao<Integer, RegionCity> {
	
	@Override
	@InsertProvider(type = RegionCitySQLProvider.class, method = "insert")
	void insert(RegionCity entity);

	@Override
	@SelectProvider(type = RegionCitySQLProvider.class, method = "selectAll")
	List<RegionCity> selectAll();
	
	@Override
	@SelectProvider(type = RegionCitySQLProvider.class, method = "selectByKey")
	RegionCity selectByKey(Integer key);
}
