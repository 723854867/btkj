package org.btkj.statistics.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.statistics.LogExploit;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.model.Exploit;
import org.btkj.pojo.model.Exploits;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.ScoreReward;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.mybatis.dao.LogExploitDao;
import org.btkj.statistics.mybatis.dao.LogScoreDao;
import org.btkj.statistics.mybatis.dao.StatisticPolicyDao;
import org.rapid.util.common.enums.TimeType;
import org.springframework.stereotype.Service;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	private LogScoreDao logScoreDao;
	@Resource
	private LogExploitDao logExploitDao;
	@Resource
	private StatisticPolicyDao statisticPolicyDao;
	
	@Override
	public List<Exploit> exploits(int employeeId, int begin, int end, int typeMod, TimeType timeType) {
		return logExploitDao.exploits(employeeId, begin, end, typeMod, timeType.name().toLowerCase());
	}
	
	@Override
	public Exploits multiExploits(List<Integer> employeeIds, int begin, int end, int bizTypeMod) {
		List<Exploit> employees = logExploitDao.multiExploits(employeeIds, begin, end, bizTypeMod);
		double performance = logExploitDao.totalExploit(employeeIds, begin, end, bizTypeMod);
		return new Exploits(performance, employees);
	}
	
	@Override
	public List<ScoreReward> scoreReward(int employeeId, int begin, int end) {
		return logScoreDao.scoreReward(employeeId, begin, end);
	}
	
	@Override
	public void recordLogScores(List<LogScore> list) {
		logScoreDao.batchInsert(list);
	}
	
	@Override
	public void logScore(LogScore logScore) {
		logScoreDao.insert(logScore);
	}
	
	@Override
	public List<LogExploit> recordLogExploits(List<LogExploit> list) {
		logExploitDao.batchInsert(list);
		return list;
	}
	
	@Override
	public void statisticPolicies(Collection<StatisticPolicy> policies) {
		statisticPolicyDao.replace(policies);
	}
	
	@Override
	public Pager<PolicyStatisticInfo> statsiticPolicies(StatisticPoliciesParam param) {
		int total = statisticPolicyDao.statisticPoliciesTotal(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		List<PolicyStatisticInfo> list = statisticPolicyDao.statisticPolicies(param);
		return new Pager<PolicyStatisticInfo>(total, list);
	}
}
