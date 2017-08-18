package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 订单详情
 * 
 * @author ahab
 */
public class ORDER_INFO extends TenantAction {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<VehicleOrder> execute(Request request, Employee employee) {
		return vehicleService.orderInfo(employee, request.getParam(Params.ORDER_ID));
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
