package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.VehicleInfosParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
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
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, VehicleInfosParam param) {
		return Result.result(vehicleService.vehicleInfos(tenant, param.getVin()));
	}
}
