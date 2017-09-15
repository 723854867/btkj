package org.btkj.common.action.tenant;

import org.btkj.common.pojo.info.EmployeeInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO extends EmployeeAction<EmployeeParam> {

	@Override
	protected Result<EmployeeInfo> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		return Result.result(new EmployeeInfo(user, employee));
	}
}
