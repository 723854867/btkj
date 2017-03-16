package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionDistrictSQLProvider;
import org.btkj.pojo.entity.RegionDistrict;
import org.rapid.data.storage.db.Dao;

public interface RegionDistrictDao extends Dao<Integer, RegionDistrict> {
	
	@Override
	@InsertProvider(type = RegionDistrictSQLProvider.class, method = "insert")
	void insert(RegionDistrict entity);

	@Override
	@SelectProvider(type = RegionDistrictSQLProvider.class, method = "selectAll")
	List<RegionDistrict> selectAll();
	
	@Override
	@SelectProvider(type = RegionDistrictSQLProvider.class, method = "selectByKey")
	RegionDistrict selectByKey(Integer key);
}
