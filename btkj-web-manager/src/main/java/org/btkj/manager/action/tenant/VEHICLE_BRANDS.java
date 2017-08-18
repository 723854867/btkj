package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.api.VehicleService;
import org.rapid.util.common.message.Result;

public class VEHICLE_BRANDS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleBrand>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(vehicleService.vehicleBrands());
	}
}
