package org.btkj.statistics.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.model.Team;
import org.btkj.statistics.model.TeamEmployee;
import org.btkj.statistics.mybatis.dao.PerformanceDao;
import org.springframework.stereotype.Service;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	private PerformanceDao performanceDao;
	
	@Override
	public Team teamPerformance(List<Integer> employeeIds, int begin, int end, int categoryMod) {
		List<TeamEmployee> employees = performanceDao.teamEmployees(employeeIds, begin, end, categoryMod);
		double performance = performanceDao.total(employeeIds, begin, end, categoryMod);
		return new Team(performance, employees);
	}
}
