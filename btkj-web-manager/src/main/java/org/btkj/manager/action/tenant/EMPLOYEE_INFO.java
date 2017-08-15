package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.EmployeeInfo;
import org.btkj.manager.pojo.param.EmployeeInfoParam;
import org.btkj.payment.api.PaymentService;
import org.btkj.payment.pojo.entity.Account;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO extends EmployeeAction<EmployeeInfoParam> {

	@Resource
	private PaymentService paymentService;
	
	@Override
	protected Result<EmployeeInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeInfoParam param) {
		EmployeeTip tip = employeeService.employeeTip(param.getTarget());
		if (null == tip)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		EmployeeTip parent = 0 == employee.getParentId() ? null : employeeService.employeeTip(employee.getParentId());
		Account account = paymentService.account(param.getTarget());
		return Result.result(new EmployeeInfo(user, tip, parent, account));
	}
}
