package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_DEPTS extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleDept>> execute(Request request, EmployeeForm employeeForm) {
		return Result.result(vehicleService.vehicleDepts(request.getParam(Params.ID)));
	}
}
