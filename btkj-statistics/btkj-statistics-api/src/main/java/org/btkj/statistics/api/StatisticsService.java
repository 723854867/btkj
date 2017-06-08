package org.btkj.statistics.api;

import java.util.List;

import org.btkj.statistics.model.Team;

public interface StatisticsService {

	/**
	 * 团队业绩
	 * 
	 * @param employeeIds
	 * @param begin
	 * @param end
	 * @param categoryMod
	 * @return
	 */
	Team teamPerformance(List<Integer> employeeIds, int begin, int end, int categoryMod);
}
