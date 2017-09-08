package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.user.EmployeeEditParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_EDIT extends EmployeeAction<EmployeeEditParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeEditParam param) {
		return userManageService.employeeEdit(employee.getTid(), employee.getId(), param);
	}
}
