package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.user.EmployeeEditParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_EDIT extends EmployeeAction<EmployeeEditParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(App app, User user, Tenant tenant, Employee employee, EmployeeEditParam param) {
		return userManageService.employeeEdit(employee.getTid(), employee.getId(), param);
	}
}
