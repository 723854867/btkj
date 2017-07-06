package org.btkj.statistics.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.mybatis.dao.LogExploitDao;
import org.btkj.statistics.mybatis.dao.LogScoreDao;
import org.btkj.statistics.pojo.model.Exploits;
import org.btkj.statistics.pojo.model.Exploit;
import org.btkj.statistics.pojo.model.ScoreReward;
import org.rapid.util.common.enums.TIME_TYPE;
import org.springframework.stereotype.Service;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	private LogScoreDao logScoreDao;
	@Resource
	private LogExploitDao logExploitDao;
	
	@Override
	public List<Exploit> exploits(int employeeId, int begin, int end, int typeMod, TIME_TYPE timeType) {
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
}
