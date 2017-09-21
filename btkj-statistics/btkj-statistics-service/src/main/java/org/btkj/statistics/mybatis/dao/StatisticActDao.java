package org.btkj.statistics.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.statistics.StatisticAct;
import org.btkj.pojo.param.statistics.Report3Param;
import org.btkj.statistics.mybatis.provider.StatisticActSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface StatisticActDao extends DBMapper<Long, StatisticAct> {

	@Override
	@InsertProvider(type = StatisticActSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insert(StatisticAct model);
	
	@SelectProvider(type = StatisticActSQLProvider.class, method = "report_3_total")
	int report_3_total(Report3Param param);
	
	@SelectProvider(type = StatisticActSQLProvider.class, method = "report_3")
	List<StatisticAct> report_3(Report3Param param);
}
