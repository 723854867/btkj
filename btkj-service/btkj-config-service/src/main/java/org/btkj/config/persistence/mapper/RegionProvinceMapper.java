package org.btkj.config.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionProvinceSQLProvider;
import org.btkj.config.pojo.entity.RegionProvince;

public interface RegionProvinceMapper {

	@SelectProvider(type = RegionProvinceSQLProvider.class, method = "selectAll")
	List<RegionProvince> selectAll();
}
