package org.btkj.bihu.vehicle.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.Insurer;
import org.btkj.bihu.vehicle.mybatis.provider.InsurerSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface InsurerDao extends Dao<Integer, Insurer> {

	@Override
	@SelectProvider(type = InsurerSQLProvider.class, method = "selectByKey")
	Insurer selectByKey(Integer key);
}
