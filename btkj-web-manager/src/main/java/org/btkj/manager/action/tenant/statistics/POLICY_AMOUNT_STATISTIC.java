package org.btkj.manager.action.tenant.statistics;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.statistics.api.StatisticsService;
import org.rapid.util.common.message.Result;

/**
 * 保单数量
 * 
 * @author ahab
 */
public class POLICY_AMOUNT_STATISTIC extends EmployeeAction<StatisticPoliciesParam> {
	
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<Pager<PolicyStatisticInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, StatisticPoliciesParam param) {
		param.setTid(tenant.getTid());
		return Result.result(statisticsService.statsiticPolicies(param));
	}
}
