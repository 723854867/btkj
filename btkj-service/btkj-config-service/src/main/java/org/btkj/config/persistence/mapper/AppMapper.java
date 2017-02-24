package org.btkj.config.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.AppSQLProvider;
import org.btkj.config.pojo.entity.App;

public interface AppMapper {

	@SelectProvider(type = AppSQLProvider.class, method = "selectAll")
	List<App> selectAll();
}
