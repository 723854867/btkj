package org.btkj.vehicle.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.vehicle.mybatis.provider.JianJieInsurerSQLProvider;
import org.btkj.vehicle.pojo.entity.JianJieInsurer;
import org.rapid.data.storage.mapper.DBMapper;

public interface JianJieInsurerDao extends DBMapper<Integer, JianJieInsurer> {
	
	@Override
	@InsertProvider(type = JianJieInsurerSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(JianJieInsurer model);
	
	@Override
	@SelectProvider(type = JianJieInsurerSQLProvider.class, method = "getByKey")
	JianJieInsurer getByKey(Integer key);

	@MapKey("id")
	@SelectProvider(type = JianJieInsurerSQLProvider.class, method = "getByTid")
	Map<Integer, JianJieInsurer> getByTid(int tid);
	
	@Override
	@DeleteProvider(type = JianJieInsurerSQLProvider.class, method = "deleteByKeys")
	void deleteByKeys(Collection<Integer> keys);
	
	@Override
	@DeleteProvider(type = JianJieInsurerSQLProvider.class, method = "delete")
	void delete(Integer key);
}
