package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.vehicle.BonusScaleConfig;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class BONUS_SCALE_CONFIGS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<BonusScaleConfig>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(vehicleManageService.bonusScaleConfigs(tenant.getTid()));
	}
}
