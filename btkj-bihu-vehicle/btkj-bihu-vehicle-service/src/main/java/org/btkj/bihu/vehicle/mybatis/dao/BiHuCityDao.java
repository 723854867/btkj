package org.btkj.bihu.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuCitySQLProvider;
import org.btkj.bihu.vehicle.pojo.entity.BiHuCity;
import org.rapid.data.storage.mapper.DBMapper;

public interface BiHuCityDao extends DBMapper<Integer, BiHuCity> {

	@Override
	@SelectProvider(type = BiHuCitySQLProvider.class, method = "getByKey")
	BiHuCity getByKey(Integer code);
	
	@SelectProvider(type = BiHuCitySQLProvider.class, method = "getAll")
	List<BiHuCity> getAll();
	
	@DeleteProvider(type = BiHuCitySQLProvider.class, method = "delete")
	void delete(int code);
}
