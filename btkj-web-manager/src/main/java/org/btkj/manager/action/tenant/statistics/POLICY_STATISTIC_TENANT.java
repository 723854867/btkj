package org.btkj.manager.action.tenant.statistics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class POLICY_STATISTIC_TENANT extends EmployeeAction<StatisticPoliciesParam> {
	
	@Resource
	private AppService appService;
	@Resource
	private TenantService tenantService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<Pager<PolicyStatisticInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, StatisticPoliciesParam param) {
		param.tenantFilter(tenant);
		Pager<PolicyStatisticInfo> pager = statisticsService.statsiticPolicies(param);
		if (!CollectionUtil.isEmpty(pager.getList())) {
			Set<Integer> apps = new HashSet<Integer>();
			Set<Integer> users = new HashSet<Integer>();
			Set<Integer> tenants = new HashSet<Integer>();
			for (PolicyStatisticInfo info : pager.getList()) {
				if (0 != info.getAppId())
					apps.add(info.getAppId());
				if (0 != info.getUid())
					users.add(info.getUid());
				if (0 != info.getTid())
					tenants.add(info.getTid());
			}
			if (!CollectionUtil.isEmpty(apps)) {
				Map<Integer, AppPO> map = appService.apps(apps);
				for (PolicyStatisticInfo info : pager.getList()) {
					AppPO temp = null == map ? null : map.get(info.getAppId());
					if (null != temp)
						info.setAname(temp.getName());
				}
			}
			if (!CollectionUtil.isEmpty(users)) {
				Map<Integer, UserPO> map = userService.users(users);
				for (PolicyStatisticInfo info : pager.getList()) {
					UserPO temp = null == map ? null : map.get(info.getUid());
					if (null != temp)
						info.setUname(temp.getName());
				}
			}
			if (!CollectionUtil.isEmpty(tenants)) {
				Map<Integer, TenantPO> map = tenantService.tenants(tenants);
				for (PolicyStatisticInfo info : pager.getList()) {
					TenantPO temp = null == map ? null : map.get(info.getTid());
					if (null != temp)
						info.setTname(temp.getName());
				}
			}
		}
		return Result.result(pager);
	}
}
