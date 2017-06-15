package org.btkj.common.action.vehicle;

import javax.annotation.Resource;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.model.VehicleOrderSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class ORDER_LIST extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Pager<VehicleOrder>> execute(Request request, Client client, EmployeeForm ef) {
		VehicleOrderSearcher searcher = request.getParam(Params.VEHICLE_ORDER_SEARCHER);
		return Result.result(vehicleService.orders(ef, searcher));
	}
}
