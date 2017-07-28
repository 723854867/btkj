package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICIES extends TenantAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Pager<VehiclePolicy>> execute(Request request, Employee employee) {
		VehiclePolicySearcher searcher = request.getParam(Params.VEHICLE_POLICY_SEARCHER);
		searcher.setTid(employee.getTid());
		return Result.result(vehicleManageService.policies(searcher));
	}
}
