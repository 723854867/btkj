package org.btkj.manager.action.tenant.statistics;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.param.statistics.Report1Param;
import org.btkj.statistics.api.StatisticsService;
import org.rapid.util.common.message.Result;

public class REPORT_1 extends EmployeeAction<Report1Param> {
	
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<List<PolicyStatisticInfo>> execute(App app, User user, Tenant tenant, Employee employee, Report1Param param) {
		param.setTid(tenant.getTid());
		return Result.result(statisticsService.report_1(param));
	}
}
