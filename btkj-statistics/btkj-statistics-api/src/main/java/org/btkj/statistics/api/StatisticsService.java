package org.btkj.statistics.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.statistics.pojo.model.ScoreReward;
import org.rapid.util.common.enums.TIME_TYPE;
import org.btkj.statistics.pojo.entity.LogExploit;
import org.btkj.statistics.pojo.entity.LogScore;
import org.btkj.statistics.pojo.model.Exploit;
import org.btkj.statistics.pojo.model.Exploits;

public interface StatisticsService {
	
	/**
	 * 获取指定的业绩记录
	 * 
	 * @return
	 */
	Map<Long, LogExploit> exploits(Collection<Long> ids);
	
	/**
	 * 雇员业绩
	 * 
	 * @param employeeId
	 * @param begin
	 * @param end
	 * @param typeMod
	 * @return
	 */
	List<Exploit> exploits(int employeeId, int begin, int end, int typeMod, TIME_TYPE timeType);

	/**
	 * 团队业绩
	 * 
	 * @param employeeIds
	 * @param begin
	 * @param end
	 * @param bizType
	 * @return
	 */
	Exploits multiExploits(List<Integer> employeeIds, int begin, int end, int bizType);
	
	/**
	 * 雇员奖励统计
	 * 
	 * @param employeeId
	 * @param begin
	 * @param end
	 * @return
	 */
	List<ScoreReward> scoreReward(int employeeId, int begin, int end);
	
	/**
	 * 统计积分日志
	 * 
	 * @param list
	 */
	void recordLogScores(List<LogScore> list);
	
	/**
	 * 记录积分日志
	 * 
	 * @param logScore
	 */
	void logScore(LogScore logScore);
	
	/**
	 * 统计业务日志
	 * 
	 * @param list
	 */
	List<LogExploit> recordLogExploits(List<LogExploit> list);
}
