package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehicleModelsParam;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.vehicle.api.VehicleService;
import org.rapid.util.common.message.Result;

public class VEHICLE_MODELS extends EmployeeAction<VehicleModelsParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleModel>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleModelsParam param) {
		return Result.result(vehicleService.vehicleModels(param.getDeptId()));
	}
}
