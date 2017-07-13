package org.btkj.bihu.vehicle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.bihu.vehicle.mybatis.provider.BiHuAreaSQLProvider;
import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.rapid.data.storage.mapper.DBMapper;

public interface BiHuAreaDao extends DBMapper<Integer, BiHuArea> {

	@Override
	@SelectProvider(type = BiHuAreaSQLProvider.class, method = "getByKey")
	BiHuArea getByKey(Integer code);
	
	@SelectProvider(type = BiHuAreaSQLProvider.class, method = "getAll")
	List<BiHuArea> getAll();
	
	@DeleteProvider(type = BiHuAreaSQLProvider.class, method = "delete")
	void delete(int code);
}
