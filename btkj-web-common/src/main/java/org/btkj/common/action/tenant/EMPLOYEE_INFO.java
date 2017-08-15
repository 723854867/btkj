package org.btkj.common.action.tenant;

import org.btkj.common.pojo.info.EmployeeInfo;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO extends EmployeeAction<EmployeeParam> {

	@Override
	protected Result<EmployeeInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(new EmployeeInfo(user, employee));
	}
}
