package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_EDIT extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(Request request, EmployeeForm employeeForm) {
		EmployeeInfo employeeInfo = request.getParam(Params.EMPLOYEE_INFO);
		return employeeService.employeeEdit(employeeInfo);
		
	}
}
