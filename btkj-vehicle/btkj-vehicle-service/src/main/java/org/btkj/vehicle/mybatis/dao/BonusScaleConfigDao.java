package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.vehicle.mybatis.provider.BonusScaleConfigSQLProvider;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusScaleConfigDao extends DBMapper<Integer, BonusScaleConfig> {
	
	@Override
	@InsertProvider(type = BonusScaleConfigSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insert(BonusScaleConfig model);
	
	@Override
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByKey")
	BonusScaleConfig getByKey(Integer key);
	
	@MapKey("id")
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByTid")
	Map<Integer, BonusScaleConfig> getByTid(int tid);
	
	@MapKey("id")
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByTidForUpdate")
	Map<Integer, BonusScaleConfig> getByTidForUpdate(int tid);
	
	@Override
	@UpdateProvider(type = BonusScaleConfigSQLProvider.class, method = "update")
	void update(BonusScaleConfig model);
	
	@Override
	@DeleteProvider(type = BonusScaleConfigSQLProvider.class, method = "delete")
	void delete(Integer key);
}
