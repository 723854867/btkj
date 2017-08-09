package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehicleOrderParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_ORDER extends EmployeeAction<VehicleOrderParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<VehicleOrder> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleOrderParam param) {
		return vehicleManageService.order(tenant.getTid(), param.getOrderId());
	}
}
