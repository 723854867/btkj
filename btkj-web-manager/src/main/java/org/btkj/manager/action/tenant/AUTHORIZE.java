package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.AuthorizeEmployeeParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE extends EmployeeAction<AuthorizeEmployeeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, AuthorizeEmployeeParam param) {
		EmployeePO target = employeeService.employeeById(param.getTarId());
		if (null == target) 
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (target.getTid() != tenant.getTid())
			return Consts.RESULT.FORBID;
		return configManageService.authorizeEmployee(tenant.getTid(), param.getTarId(), param.getModulars());
	}
}
