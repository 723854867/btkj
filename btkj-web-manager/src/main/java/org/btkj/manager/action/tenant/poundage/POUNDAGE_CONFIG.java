package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.PoundageConfigParam;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.NodeConfigModel;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.message.Result;

public class POUNDAGE_CONFIG extends EmployeeAction<PoundageConfigParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<NodeConfigModel> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageConfigParam param) {
		return Result.result(bonusService.poundageConfig(tenant.getTid(), param.getInsurerId(), param.getNodeId(), param.getCoefficientId()));
	}
}
