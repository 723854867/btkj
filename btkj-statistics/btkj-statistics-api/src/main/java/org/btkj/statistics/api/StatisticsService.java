package org.btkj.statistics.api;

import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.statistics.LogExploit;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.info.statistics.ExploitInfo;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.info.statistics.Report_1_Info;
import org.btkj.pojo.model.Exploits;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.Report1Param;
import org.btkj.pojo.param.statistics.Report2Param;
import org.btkj.pojo.param.statistics.Report3Param;
import org.btkj.pojo.param.statistics.StatisticScoreParam;

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
	ExploitInfo exploits(int employeeId, int begin, int end);

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
	
	Pager<LogScore> scores(StatisticScoreParam param);
	
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
	
	List<PolicyStatisticInfo> report_1(Report1Param param);
	
	Pager<PolicyStatisticInfo> report_2(Report2Param param);
	
	void quoteRecord(Employee employee, String vin, int usedType);
	
	Pager<Report_1_Info> report_3(Report3Param param);
}
