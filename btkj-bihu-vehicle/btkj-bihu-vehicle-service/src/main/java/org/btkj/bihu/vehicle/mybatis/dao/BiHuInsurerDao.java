package org.btkj.bihu.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuInsurerSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface BiHuInsurerDao extends DBMapper<Integer, BiHuInsurer> {

	@Override
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "getByKey")
	BiHuInsurer getByKey(Integer key);
	
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "getByCode")
	BiHuInsurer getByCode(int code);
	
	@SelectProvider(type = BiHuInsurerSQLProvider.class, method = "getWithinKey")
	List<BiHuInsurer> getWithinKey(@Param("list") List<Integer> keys);
}
