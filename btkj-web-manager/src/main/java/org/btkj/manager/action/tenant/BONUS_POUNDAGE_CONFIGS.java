package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.message.Result;

/**
 * 车险佣金系数设置结构
 * 
 * @author ahab
 */
public class BONUS_POUNDAGE_CONFIGS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private BonusService bonusService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(bonusService.bonusPoundageConfigs());
	}
}
