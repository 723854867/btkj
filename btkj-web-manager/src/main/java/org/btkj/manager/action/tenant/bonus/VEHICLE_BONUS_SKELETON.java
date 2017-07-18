package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

/**
 * 车险佣金系数设置结构
 * 
 * @author ahab
 */
public class VEHICLE_BONUS_SKELETON extends TenantAction {
	
	@Resource
	private BonusService bonusService;
	
	@Override
	protected Result<BonusRouteView> execute(Request request, EmployeeForm employeeForm) {
		return Result.result(bonusService.bonusRouteInfo());
	}
}
