package org.btkj.common.action.tenant.statistics;

import javax.annotation.Resource;

import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.StatisticScoreParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 雇员积分奖励统计
 * 
 * @author ahab
 */
public class STATISTIC_SCORE extends EmployeeAction<StatisticScoreParam> {
	
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<Pager<LogScore>> execute(App app, User user, Tenant tenant, Employee employee, StatisticScoreParam param) {
		param.setTarId(employee.getId());
		return Result.result(statisticsService.scores(param));
	}
}
