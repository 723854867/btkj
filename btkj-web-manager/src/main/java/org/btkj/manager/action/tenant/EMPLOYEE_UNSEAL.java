package org.btkj.manager.action.tenant;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.EmployeeIdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_UNSEAL extends EmployeeAction<EmployeeIdParam> {

	@Override
	protected Result<Void> execute(App app, User user, Tenant tenant, Employee employee, EmployeeIdParam param) {
		if (employee.getId() == param.getId())
			return Consts.RESULT.FORBID;
		return employeeService.unseal(app.getId(), tenant.getTid(), param.getId());
	}
}
