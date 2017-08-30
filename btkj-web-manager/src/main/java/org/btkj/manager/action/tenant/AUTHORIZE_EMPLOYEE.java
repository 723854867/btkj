package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.AuthorizeEmployeeParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE_EMPLOYEE extends EmployeeAction<AuthorizeEmployeeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, AuthorizeEmployeeParam param) {
		if (employee.getId() == param.getTarId())
			return Consts.RESULT.FORBID;
		EmployeePO target = employeeService.employeeById(param.getTarId());
		if (null == target) 
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (target.getTid() != tenant.getTid())
			return Consts.RESULT.FORBID;
		configManageService.authorize(param.getTarId(), param.getModulars(), ModularType.EMPLOYEE);
		return Consts.RESULT.OK;
	}
}
