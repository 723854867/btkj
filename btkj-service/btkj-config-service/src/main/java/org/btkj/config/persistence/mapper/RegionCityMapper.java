package org.btkj.config.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.RegionCitySQLProvider;
import org.btkj.config.pojo.entity.RegionCity;

public interface RegionCityMapper {

	@SelectProvider(type = RegionCitySQLProvider.class, method = "selectAll")
	List<RegionCity> selectAll();
}
