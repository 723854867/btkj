package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.bo.Pager;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleOrderListInfo;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_ORDERS extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Pager<VehicleOrderListInfo>> execute(Request request, EmployeeForm ef) {
		VehicleOrderSearcher searcher = request.getParam(Params.VEHICLE_ORDER_SEARCHER);
		return Result.result(vehicleService.orders(ef, searcher));
	}
}
