package org.btkj.common.action.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.model.Team;
import org.btkj.statistics.model.TeamEmployee;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 我的团队分页
 * 
 * @author ahab
 */
public class TEAM_LIST extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		List<Employee> list = employeeService.team(employeeForm);
		if (CollectionUtils.isEmpty(list))
			return Result.result(Collections.EMPTY_LIST);
		List<Integer> employeeIds = new ArrayList<Integer>();
		for (Employee employee : list)
			employeeIds.add(employee.getId());
		
		Team team = statisticsService.teamPerformance(employeeIds, request.getParam(Params.BEGIN_TIME), 
				request.getParam(Params.END_TIME), request.getParam(Params.MOD));
		
		return null;
	}
}
