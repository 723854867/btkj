package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.AuthorizeEmployeeParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.ModularType;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE_EMPLOYEE extends EmployeeAction<AuthorizeEmployeeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(App app, User user, Tenant tenant, Employee employee, AuthorizeEmployeeParam param) {
		if (employee.getId() == param.getTarId())
			return Consts.RESULT.FORBID;
		Employee target = employeeService.employeeById(param.getTarId());
		if (null == target) 
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (target.getTid() != tenant.getTid())
			return Consts.RESULT.FORBID;
		configManageService.authorize(param.getTarId(), param.getModulars(), ModularType.EMPLOYEE);
		return Consts.RESULT.OK;
	}
}
