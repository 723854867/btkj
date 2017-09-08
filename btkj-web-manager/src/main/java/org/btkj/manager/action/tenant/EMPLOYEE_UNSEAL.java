package org.btkj.manager.action.tenant;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.EmployeeIdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_UNSEAL extends EmployeeAction<EmployeeIdParam> {

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		if (employee.getId() == param.getId())
			return Consts.RESULT.FORBID;
		return employeeService.unseal(app.getId(), tenant.getTid(), param.getId());
	}
}
