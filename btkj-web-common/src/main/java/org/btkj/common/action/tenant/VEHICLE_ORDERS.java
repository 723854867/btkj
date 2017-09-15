package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.VehicleOrderListInfo;
import org.btkj.pojo.param.vehicle.VehicleOrdersParam;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class VEHICLE_ORDERS extends EmployeeAction<VehicleOrdersParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Pager<VehicleOrderListInfo>> execute(App app, User user, Tenant tenant, Employee employee, VehicleOrdersParam param) {
		param.setUid(user.getUid());
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		param.setEmployeeId(employee.getId());
		return Result.result(vehicleService.orders(tenant, employee, param));
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
