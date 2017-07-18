package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_MODELS extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleModel>> execute(Request request, EmployeeForm employeeForm) {
		return Result.result(vehicleService.vehicleModels(request.getParam(Params.ID)));
	}
}
