package org.btkj.bihu.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuInsurerSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface BiHuInsurerDao extends Dao<Integer, BiHuInsurer> {

	@Override
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "selectByKey")
	BiHuInsurer selectByKey(Integer key);
	
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "selectByCode")
	BiHuInsurer selectByCode(int code);
	
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "selectWithinKey")
	List<BiHuInsurer> selectWithinKey(@Param("list") List<Integer> keys);
}
