package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehicleDeptsParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.vehicle.api.VehicleService;
import org.rapid.util.common.message.Result;

public class VEHICLE_DEPTS extends EmployeeAction<VehicleDeptsParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleDept>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleDeptsParam param) {
		return Result.result(vehicleService.vehicleDepts(param.getBrandId()));
	}
}
