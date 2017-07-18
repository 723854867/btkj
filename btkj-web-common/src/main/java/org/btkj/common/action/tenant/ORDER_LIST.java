package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleOrderListInfo;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class ORDER_LIST extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Pager<VehicleOrderListInfo>> execute(Request request, Employee employee) {
		VehicleOrderSearcher searcher = request.getParam(Params.VEHICLE_ORDER_SEARCHER);
		searcher.setEmployeeId(employee.getId());
		return Result.result(vehicleService.orders(employee, searcher));
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
