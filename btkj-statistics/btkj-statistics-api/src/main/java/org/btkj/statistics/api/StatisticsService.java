package org.btkj.statistics.api;

import java.util.List;

import org.btkj.statistics.pojo.model.ScoreReward;
import org.rapid.util.common.enums.TIME_TYPE;
import org.btkj.statistics.pojo.model.Exploit;
import org.btkj.statistics.pojo.model.Exploits;

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
}
