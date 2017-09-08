package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.EmployeeInfo;
import org.btkj.payment.api.PaymentService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.payment.Account;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.EmployeeIdParam;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO extends EmployeeAction<EmployeeIdParam> {

	@Resource
	private PaymentService paymentService;
	
	@Override
	protected Result<EmployeeInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		EmployeeTip tip = employeeService.employeeTip(param.getId());
		if (null == tip)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		EmployeeTip parent = 0 == employee.getParentId() ? null : employeeService.employeeTip(employee.getParentId());
		Account account = paymentService.account(param.getId());
		return Result.result(new EmployeeInfo(tip, parent, account));
	}
}
