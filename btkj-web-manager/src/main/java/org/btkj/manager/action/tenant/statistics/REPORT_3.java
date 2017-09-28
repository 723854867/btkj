package org.btkj.manager.action.tenant.statistics;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.statistics.Report_1_Info;
import org.btkj.pojo.param.statistics.Report3Param;
import org.btkj.statistics.api.StatisticsService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 业务员报价、保单、保费、成交率报表
 * 
 * @author ahab
 */
public class REPORT_3 extends EmployeeAction<Report3Param> {
	
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<List<Report_1_Info>> execute(App app, User user, Tenant tenant, Employee employee, Report3Param param) {
		param.setTid(tenant.getTid());
		List<Report_1_Info> list = statisticsService.report_3(param);
		Set<Integer> set = new HashSet<Integer>();
		for (Report_1_Info info : list)
			set.add(info.getUid());
		if (!CollectionUtil.isEmpty(set)) {
			Map<Integer, User> users = userService.users(set);
			for (Report_1_Info info : list) {
				User temp = users.get(info.getUid());
				if (null != temp)
					info.setName(temp.getName());
			}
		}
 		return Result.result(list);
	}
}
