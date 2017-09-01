package org.btkj.manager.action.tenant.poundage;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.EmployeeIdParam;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.CoefficientDocument;
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
