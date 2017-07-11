package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.provider.BonusManageConfigSQLProvider;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusManageConfigDao extends DBMapper<String, BonusManageConfig> {
	
	@Override
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "insert")
	void insert(BonusManageConfig model);
	
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "getByTid")
	List<BonusManageConfig> getByTid(int tid);
}
