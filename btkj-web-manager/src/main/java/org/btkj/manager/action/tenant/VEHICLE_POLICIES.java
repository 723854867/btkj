package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.vehicle.VehiclePoliciesParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICIES extends EmployeeAction<VehiclePoliciesParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Pager<VehiclePolicy>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehiclePoliciesParam param) {
		param.setTid(tenant.getTid());
		return Result.result(vehicleManageService.policies(param));
	}
}
