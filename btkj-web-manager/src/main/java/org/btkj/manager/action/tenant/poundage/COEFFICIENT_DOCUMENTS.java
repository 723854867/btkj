package org.btkj.manager.action.tenant.poundage;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.CoefficientDocument;
import org.btkj.pojo.param.EmployeeIdParam;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;

import com.google.gson.Gson;

public class COEFFICIENT_DOCUMENTS extends EmployeeAction<EmployeeIdParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<Map<Integer, CoefficientDocument>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeIdParam param) {
		return bonusService.coefficientDocuments(param.getId());
	}
	
	@Override
	public Gson gson() {
		return SerializeUtil.JsonUtil.EXPOSE_GSON;
	}
}
