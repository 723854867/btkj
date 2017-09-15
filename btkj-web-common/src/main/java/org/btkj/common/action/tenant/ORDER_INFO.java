package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.EmployeeSidParam;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 订单详情
 * 
 * @author ahab
 */
public class ORDER_INFO extends EmployeeAction<EmployeeSidParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, EmployeeSidParam param) {
		return vehicleService.orderInfo(tenant, employee, param.getId());
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
