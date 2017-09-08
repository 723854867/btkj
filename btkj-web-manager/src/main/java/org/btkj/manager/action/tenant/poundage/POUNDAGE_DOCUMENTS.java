package org.btkj.manager.action.tenant.poundage;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.PoundageDocument;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.api.BonusService;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;

import com.google.gson.Gson;

public class POUNDAGE_DOCUMENTS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<Map<Integer, PoundageDocument>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(bonusService.poundageDocuments());
	}
	
	@Override
	public Gson gson() {
		return SerializeUtil.JsonUtil.EXPOSE_GSON;
	}
}
