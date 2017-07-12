package org.btkj.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.provider.BonusScaleConfigSQLProvider;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusScaleConfigDao extends DBMapper<Integer, BonusScaleConfig> {

	@SelectProvider(type = BonusScaleConfigSQLProvider.class, method = "getByTid")
	List<BonusScaleConfig> getByTid(int tid);
}
