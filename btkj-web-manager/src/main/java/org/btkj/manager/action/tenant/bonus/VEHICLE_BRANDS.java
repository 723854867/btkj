package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_BRANDS extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		return Result.result(vehicleService.vehicleBrands());
	}
}
