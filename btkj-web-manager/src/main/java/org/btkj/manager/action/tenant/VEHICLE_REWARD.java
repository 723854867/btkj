package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 奖励结算
 * 
 * @author ahab
 */
public class VEHICLE_REWARD extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		List<VehicleOrder> orders = vehicleManageService.orders(tenant.getTid(), VehicleOrderState.ISSUED.mark());
		List<BonusManageConfig> configs = vehicleManageService.bonusManageConfigs(tenant.getTid());
		return null;
	}
}
