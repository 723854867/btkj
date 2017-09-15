package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.VehiclePolicyParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;

public class VEHICLE_POLICY extends EmployeeAction<VehiclePolicyParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<VehiclePolicy> execute(App app, User user, Tenant tenant, Employee employee, VehiclePolicyParam param) {
		return vehicleManageService.policy(tenant.getTid(), param.getPolicyId());
	}
}
