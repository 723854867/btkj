package org.btkj.vehicle.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.vehicle.PoundageCoefficientRange;
import org.btkj.vehicle.mybatis.provider.PoundageCoefficientRangeSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface PoundageCoefficientRangeDao extends DBMapper<Integer, PoundageCoefficientRange> {

	@Override
	@InsertProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(PoundageCoefficientRange model);
	
	@Override
	@SelectProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "getByKey")
	PoundageCoefficientRange getByKey(Integer key);
	
	@MapKey("id")
	@SelectProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "getByTidAndCfgCoefficientId")
	Map<Integer, PoundageCoefficientRange> getByTidAndCfgCoefficientId(@Param("tid") int tid, @Param("cfgCoefficientId") int cfgCoefficientId);
	
	@MapKey("id")
	@SelectProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "getByTidAndCfgCoefficientIdForUpdate")
	Map<Integer, PoundageCoefficientRange> getByTidAndCfgCoefficientIdForUpdate(@Param("tid") int tid, @Param("cfgCoefficientId") int cfgCoefficientId);

	@Override
	@UpdateProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "update")
	void update(PoundageCoefficientRange model);
	
	@Override
	@DeleteProvider(type = PoundageCoefficientRangeSQLProvider.class, method = "delete")
	void delete(Integer key);
}
