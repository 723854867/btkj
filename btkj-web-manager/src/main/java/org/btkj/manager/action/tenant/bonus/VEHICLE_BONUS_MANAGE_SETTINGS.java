package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_BONUS_MANAGE_SETTINGS extends TenantAction {
	
	@Resource
	private VehicleConfigService vehicleConfigService;
	
	@Override
	protected Result<List<BonusManageConfig>> execute(Request request, EmployeeForm ef) {
		return Result.result(vehicleConfigService.bonusManageConfigs(ef.getTenant().getTid()));
	}
}
