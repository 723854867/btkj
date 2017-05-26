package org.btkj.common.action.vehicle;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
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
	protected Result<?> execute(Request request, Client client, EmployeeForm employeeForm) {
		return vehicleService.orderInfo(employeeForm, request.getParam(Params.LICENSE));
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
