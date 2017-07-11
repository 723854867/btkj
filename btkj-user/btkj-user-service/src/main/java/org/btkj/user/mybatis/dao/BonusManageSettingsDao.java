package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.user.mybatis.provider.BonusManageSettingsSQLProvider;
import org.btkj.user.pojo.entity.BonusManageSettings;
import org.rapid.data.storage.mapper.DBMapper;

public interface BonusManageSettingsDao extends DBMapper<String, BonusManageSettings> {
	
	@Override
	@SelectProvider(type = BonusManageSettingsSQLProvider.class, method = "getByKey")
	BonusManageSettings getByKey(String key);

	@Override
	@SelectProvider(type = BonusManageSettingsSQLProvider.class, method = "insert")
	void insert(BonusManageSettings model);
	
	@SelectProvider(type = BonusManageSettingsSQLProvider.class, method = "getByTid")
	List<BonusManageSettings> getByTid(int tid);
}
