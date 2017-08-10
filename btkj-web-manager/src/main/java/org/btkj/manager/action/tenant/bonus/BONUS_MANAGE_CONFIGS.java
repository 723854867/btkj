package org.btkj.manager.action.tenant.bonus;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.rapid.util.common.message.Result;

/**
 * 管理佣金设置
 * 
 * @author ahab
 */
public class BONUS_MANAGE_CONFIGS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(new ArrayList<BonusManageConfig>(vehicleManageService.bonusManageConfigs(tenant.getTid()).values()));
	}
}
