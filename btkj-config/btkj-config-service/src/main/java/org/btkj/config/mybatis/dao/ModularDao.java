package org.btkj.config.mybatis.dao;

import java.util.Map;
import java.util.Set;

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
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getAll")
	Map<String, Modular> getAll();
	
	@Override
	@SelectProvider(type = ModularSQLProvider.class, method = "getByKey")
	Modular getByKey(String key);
	
	/**
	 * 获取所有子节点
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getChildren")
	Map<String, Modular> getChildren(@Param("left") int left, @Param("right") int right);
	
	@Override
	@UpdateProvider(type = ModularSQLProvider.class, method = "update")
	void update(Modular model);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForInsert")
	void updateForInsert(@Param("threshold") int threshold, @Param("step") int step);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForMove")
	void updateForMove(@Param("step") int step, @Param("start") int start, @Param("end") int end, @Param("set") Set<String> set);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForLeftMove")
	void updateForLeftMove(@Param("step") int step, @Param("PR") int PR, @Param("CL") int CL, @Param("set") Set<String> set);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForRightMove")
	void updateForRightMove(@Param("step") int step, @Param("PR") int PR, @Param("CR") int CR, @Param("set") Set<String> set);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForDelete")
	void updateForDelete(@Param("start") int start, @Param("step") int step);
	
	@Override
	@DeleteProvider(type = ModularSQLProvider.class, method = "delete")
	void delete(String key);
}
