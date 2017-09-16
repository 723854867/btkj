package org.btkj.statistics.api;

import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.statistics.LogExploit;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.info.statistics.StatisticScoreInfo;
import org.btkj.pojo.model.Exploit;
import org.btkj.pojo.model.Exploits;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.ScoreReward;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.pojo.param.statistics.StatisticScoreParam;
import org.rapid.util.common.enums.TimeType;

public interface StatisticsService {
	
	/**
	 * 雇员业绩
	 * 
	 * @param employeeId
	 * @param begin
	 * @param end
	 * @param typeMod
	 * @return
	 */
	List<Exploit> exploits(int employeeId, int begin, int end, int typeMod, TimeType timeType);

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
	
	Pager<StatisticScoreInfo> scores(StatisticScoreParam param);
	
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
	
	/**
	 * 统计保单插入
	 * 
	 * @param policies
	 */
	void statisticPolicies(Collection<StatisticPolicy> policies);
	
	/**
	 * 保单统计
	 * 
	 * @param param
	 */
	Pager<PolicyStatisticInfo> statsiticPolicies(StatisticPoliciesParam param);
}
