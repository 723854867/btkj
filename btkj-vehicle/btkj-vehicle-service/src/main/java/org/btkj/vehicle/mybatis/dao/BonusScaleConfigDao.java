package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.vehicle.mybatis.provider.BonusScaleConfigSQLProvider;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusScaleConfigDao extends DBMapper<Integer, BonusScaleConfig> {
	
	@Override
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "insert")
	void insert(BonusScaleConfig model);
	
	@Override
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByKey")
	BonusScaleConfig getByKey(Integer key);
	
	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByTid")
	List<BonusScaleConfig> getByTid(int tid);
	
	@Override
	@UpdateProvider(type = BonusScaleConfigSQLProvider.class, method = "update")
	void update(BonusScaleConfig model);
	
	@Override
	@DeleteProvider(type = BonusScaleConfigSQLProvider.class, method = "delete")
	void delete(Integer key);
}
