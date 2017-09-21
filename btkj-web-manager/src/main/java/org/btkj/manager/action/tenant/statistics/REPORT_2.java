package org.btkj.manager.action.tenant.statistics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.Report2Param;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class REPORT_2 extends EmployeeAction<Report2Param> {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<Pager<PolicyStatisticInfo>> execute(App app, User user, Tenant tenant, Employee employee, Report2Param param) {
		param.setTid(tenant.getTid());
		Pager<PolicyStatisticInfo> pager = statisticsService.report_2(param);
		if (!CollectionUtil.isEmpty(pager.getList())) {
			Set<Integer> insurers = new HashSet<Integer>();
			for (PolicyStatisticInfo info : pager.getList()) {
				if (0 != info.getInsurerId())
					insurers.add(info.getInsurerId());
			}
			if (!CollectionUtil.isEmpty(insurers)) {
				Map<Integer, Insurer> map = configService.insurers(insurers);
				for (PolicyStatisticInfo info : pager.getList()) {
					Insurer temp = null == map ? null : map.get(info.getInsurerId());
					if (null != temp)
						info.setInsurerName(temp.getName());
				}
			}
		}
		return Result.result(pager);
	}
}
