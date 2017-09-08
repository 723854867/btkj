package org.btkj.manager.action.tenant.poundage;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.vehicle.CoefficientRange;
import org.btkj.pojo.param.EmployeeIdParam;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.message.Result;

public class COEFFICIENT_RANGES extends EmployeeAction<EmployeeIdParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<Map<Integer, CoefficientRange>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		return Result.result(bonusService.coefficientRanges(tenant.getTid(), param.getId()));
	}
}
