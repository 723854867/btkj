package org.btkj.manager.action.tenant;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.config.ModularDocument;
import org.btkj.pojo.param.EmployeeIdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class MODULARS_EMPLOYEE extends EmployeeAction<EmployeeIdParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		if (!BtkjUtil.isTopRole(employee))
			return Consts.RESULT.NO_PRIVILEGE;
		if (param.getId() == employee.getId())
			return Consts.RESULT.FORBID;
		EmployeePO target = employeeService.employeeById(param.getId());
		if (null == target)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (target.getTid() != employee.getTid())
			return Consts.RESULT.FORBID;
		return Result.result(configManageService.modulars(param.getId(), ModularType.EMPLOYEE));
	}
}
