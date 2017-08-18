package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleOrderListInfo;
import org.btkj.vehicle.pojo.param.VehicleOrdersParam;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class VEHICLE_ORDERS extends EmployeeAction<VehicleOrdersParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Pager<VehicleOrderListInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleOrdersParam param) {
		param.setUid(user.getUid());
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		param.setEmployeeId(employee.getId());
		return Result.result(vehicleService.orders(tenant, param));
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
