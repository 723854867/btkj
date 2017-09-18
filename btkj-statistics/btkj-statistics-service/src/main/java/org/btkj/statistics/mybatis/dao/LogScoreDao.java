package org.btkj.statistics.mybatis.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.param.statistics.StatisticScoreParam;
import org.btkj.statistics.mybatis.provider.LogScoreSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface LogScoreDao extends DBMapper<Long, LogScore> {
	
	@Override
	@InsertProvider(type = LogScoreSQLProvider.class, method = "insert")
	void insert(LogScore model);
	
	@InsertProvider(type = LogScoreSQLProvider.class, method = "batchInsert")
	void batchInsert(Collection<LogScore> list);
	
	@SelectProvider(type = LogScoreSQLProvider.class, method = "total")
	int total(StatisticScoreParam param);
	
	@SelectProvider(type = LogScoreSQLProvider.class, method = "scores")
	List<LogScore> scores(StatisticScoreParam param);
}
