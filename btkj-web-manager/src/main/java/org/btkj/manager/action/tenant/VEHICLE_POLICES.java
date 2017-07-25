package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICES extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		VehiclePolicySearcher searcher = request.getParam(Params.VEHICLE_POLICY_SEARCHER);
		return null;
	}
}
