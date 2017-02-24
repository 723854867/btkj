package org.btkj.config.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionDistrictSQLProvider;
import org.btkj.config.pojo.entity.RegionDistrict;

public interface RegionDistrictMapper {

	@SelectProvider(type = RegionDistrictSQLProvider.class, method = "selectAll")
	List<RegionDistrict> selectAll();
}
