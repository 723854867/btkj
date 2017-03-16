package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionProvinceSQLProvider;
import org.btkj.pojo.entity.RegionProvince;
import org.rapid.data.storage.db.Dao;

public interface RegionProvinceDao extends Dao<Integer, RegionProvince> {
	
	@Override
	@InsertProvider(type = RegionProvinceSQLProvider.class, method = "insert")
	void insert(RegionProvince entity);

	@Override
	@SelectProvider(type = RegionProvinceSQLProvider.class, method = "selectAll")
	List<RegionProvince> selectAll();
	
	@Override
	@SelectProvider(type = RegionProvinceSQLProvider.class, method = "selectByKey")
	RegionProvince selectByKey(Integer key);
}
