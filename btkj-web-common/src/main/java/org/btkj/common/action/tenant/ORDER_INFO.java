package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
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
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeSidParam param) {
		return vehicleService.orderInfo(tenant, employee, param.getId());
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}

}
