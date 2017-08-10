package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
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
