package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICY_INFO extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<VehiclePolicy> execute(Request request, Employee employee) {
		return vehicleManageService.policy(employee.getTid(), request.getParam(Params.ORDER_ID));
	}
}
