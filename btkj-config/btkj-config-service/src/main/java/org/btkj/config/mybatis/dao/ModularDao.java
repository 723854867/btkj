package org.btkj.config.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.ModularSQLProvider;
import org.btkj.config.pojo.entity.Modular;
import org.rapid.data.storage.mapper.DBMapper;

public interface ModularDao extends DBMapper<String, Modular> {

	@Override
	@InsertProvider(type = ModularSQLProvider.class, method = "insert")
	void insert(Modular model);
	
	@Override
	@MapKey("key")
	@SelectProvider(type = ModularSQLProvider.class, method = "getAll")
	Map<String, Modular> getAll();
	
	@Override
	@SelectProvider(type = ModularSQLProvider.class, method = "getByKey")
	Modular getByKey(String key);
	
	@Override
	@UpdateProvider(type = ModularSQLProvider.class, method = "update")
	void update(Modular model);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForInsert")
	void updateForInsert(@Param("threshold") int threshold, @Param("step") int step);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForLeftMove")
	void updateForLeftMove(@Param("step") int step, @Param("step1") int step1, @Param("PL") int PL, @Param("CL") int CL, @Param("CR") int CR);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForRightMove")
	void updateForRightMove(@Param("step") int step, @Param("step1") int step1, @Param("PR") int PR, @Param("CL") int CL, @Param("CR") int CR);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForDelete")
	void updateForDelete(@Param("start") int start, @Param("step") int step);
	
	@Override
	@DeleteProvider(type = ModularSQLProvider.class, method = "delete")
	void delete(String key);
}
