package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.VehicleInfosParam;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 根据车架号获取车辆信息 
 * 
 * @author ahab
 */
public class VEHICLE_INFOS extends EmployeeAction<VehicleInfosParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleInfosParam param) {
		return Result.result(vehicleService.vehicleInfos(tenant, param.getVin()));
	}
}
