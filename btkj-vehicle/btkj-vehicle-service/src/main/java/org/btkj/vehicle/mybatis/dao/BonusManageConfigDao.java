package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.vehicle.mybatis.provider.BonusManageConfigSQLProvider;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusManageConfigDao extends DBMapper<String, BonusManageConfig> {
	
	@Override
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "insert")
	void insert(BonusManageConfig model);
	
	@Override
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "getByKey")
	BonusManageConfig getByKey(String key);
	
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "getByTid")
	List<BonusManageConfig> getByTid(int tid);
	
	@Override
	@UpdateProvider(type = BonusManageConfigSQLProvider.class, method = "update")
	void update(BonusManageConfig model);
	
	@Override
	@DeleteProvider(type = BonusManageConfigSQLProvider.class, method = "delete")
	void delete(String key);
}
