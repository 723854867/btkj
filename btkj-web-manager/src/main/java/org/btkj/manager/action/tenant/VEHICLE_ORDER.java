package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehicleOrderParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_ORDER extends EmployeeAction<VehicleOrderParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<VehicleOrder> execute(App app, User user, Tenant tenant, Employee employee, VehicleOrderParam param) {
		return vehicleManageService.order(tenant.getTid(), param.getOrderId());
	}
}
