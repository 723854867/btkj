package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.vehicle.BonusManageConfig;
import org.btkj.vehicle.mybatis.provider.BonusManageConfigSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusManageConfigDao extends DBMapper<String, BonusManageConfig> {
	
	@Override
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "insert")
	void insert(BonusManageConfig model);
	
	@Override
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "getByKey")
	BonusManageConfig getByKey(String key);
	
	@MapKey("key")
	@SelectProvider(type = BonusManageConfigSQLProvider.class, method = "getByTid")
	Map<String, BonusManageConfig> getByTid(int tid);
	
	@Override
	@UpdateProvider(type = BonusManageConfigSQLProvider.class, method = "update")
	void update(BonusManageConfig model);
	
	@Override
	@DeleteProvider(type = BonusManageConfigSQLProvider.class, method = "delete")
	void delete(String key);
}
