package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehiclePolicyParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICY extends EmployeeAction<VehiclePolicyParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<VehiclePolicy> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehiclePolicyParam param) {
		return vehicleManageService.policy(tenant.getTid(), param.getPolicyId());
	}
}