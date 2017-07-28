package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_BONUS_MANAGE_SETTINGS extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<List<BonusManageConfig>> execute(Request request, Employee employee) {
		return Result.result(vehicleManageService.bonusManageConfigs(employee.getTid()));
	}
}
