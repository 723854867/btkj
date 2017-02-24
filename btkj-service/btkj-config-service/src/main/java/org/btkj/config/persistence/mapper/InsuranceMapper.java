package org.btkj.config.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.InsuranceSQLMapper;
import org.btkj.config.pojo.entity.Insurance;

public interface InsuranceMapper {

	@SelectProvider(type = InsuranceSQLMapper.class, method = "selectAll")
	List<Insurance> selectAll();
}
