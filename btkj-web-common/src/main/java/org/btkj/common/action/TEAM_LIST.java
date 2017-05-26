package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 我的团队分页
 * 
 * @author ahab
 */
public class TEAM_LIST extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		Pager<Employee> pager = employeeService.team(employeeForm);
		return null;
	}
}
