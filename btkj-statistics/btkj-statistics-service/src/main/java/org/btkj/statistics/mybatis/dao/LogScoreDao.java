package org.btkj.statistics.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.statistics.mybatis.provider.LogScoreSQLProvider;
import org.btkj.statistics.pojo.entity.LogScore;
import org.btkj.statistics.pojo.model.ScoreReward;
import org.rapid.data.storage.mapper.DBMapper;

public interface LogScoreDao extends DBMapper<Long, LogScore> {
	
	/**
	 * 雇员积分统计
	 * 
	 * @param searcher
	 * @return
	 */
	@SelectProvider(type = LogScoreSQLProvider.class, method = "scoreReward")
	List<ScoreReward> scoreReward(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("end") int end);
}