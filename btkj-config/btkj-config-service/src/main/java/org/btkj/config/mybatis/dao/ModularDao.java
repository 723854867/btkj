package org.btkj.config.mybatis.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.ModularSQLProvider;
import org.btkj.pojo.entity.config.Modular;
import org.rapid.data.storage.mapper.DBMapper;

public interface ModularDao extends DBMapper<Integer, Modular> {

	@Override
	@InsertProvider(type = ModularSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Modular model);
	
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getByType")
	Map<Integer, Modular> getByType(int type);
	
	@Override
	@SelectProvider(type = ModularSQLProvider.class, method = "getByKey")
	Modular getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getByKeys")
	Map<Integer, Modular> getByKeys(Collection<Integer> keys);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getAll")
	Map<Integer, Modular> getAll();
	
	/**
	 * 获取所有子节点
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	@MapKey("id")
	@SelectProvider(type = ModularSQLProvider.class, method = "getChildren")
	Map<Integer, Modular> getChildren(@Param("left") int left, @Param("right") int right, @Param("type") int type);
	
	@Override
	@UpdateProvider(type = ModularSQLProvider.class, method = "update")
	void update(Modular model);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForInsert")
	void updateForInsert(@Param("threshold") int threshold, @Param("step") int step, @Param("type") int type);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForMove")
	void updateForMove(@Param("step") int step, @Param("start") int start, @Param("end") int end, @Param("set") Set<Integer> set, @Param("type") int type);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForLeftMove")
	void updateForLeftMove(@Param("step") int step, @Param("PR") int PR, @Param("CL") int CL, @Param("set") Set<Integer> set, @Param("type") int type);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForRightMove")
	void updateForRightMove(@Param("step") int step, @Param("PR") int PR, @Param("CR") int CR, @Param("set") Set<Integer> set, @Param("type") int type);
	
	@UpdateProvider(type = ModularSQLProvider.class, method = "updateForDelete")
	void updateForDelete(@Param("start") int start, @Param("step") int step, @Param("type") int type);
	
	@DeleteProvider(type = ModularSQLProvider.class, method = "delete")
	void delete(@Param("start") int start, @Param("end") int end, @Param("type") int type);
}
