package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.EmployeeEditParam;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_EDIT extends EmployeeAction<EmployeeEditParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeEditParam param) {
		return userManageService.employeeEdit(employee.getTid(), employee.getId(), param);
	}
}
