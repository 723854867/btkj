package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.param.VehiclePoliciesParam;
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
