package org.btkj.manager.action.tenant.poundage;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.EmployeeIdParam;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.CoefficientRange;
import org.rapid.util.common.message.Result;

/**
 * 获取手续费系数可选项
 * 
 * @author ahab
 */
public class POUNDAGE_COEFFICIENT_RANGES extends EmployeeAction<EmployeeIdParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<List<CoefficientRange>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		return bonusService.poundageCoefficientRanges(tenant.getTid(), param.getId());
	}
}
