package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO_SAVE extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(Request request, App app, Tenant tenant, User user) {
		EmployeeInfo employeeInfo = request.getParam(Params.EMPLOYEE_INFO);
		return employeeService.employeeInfoSave(employeeInfo);
		
	}
}
