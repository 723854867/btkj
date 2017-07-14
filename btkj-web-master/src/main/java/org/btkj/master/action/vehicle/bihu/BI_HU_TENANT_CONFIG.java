package org.btkj.master.action.vehicle.bihu;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class BI_HU_TENANT_CONFIG extends AdministratorAction {
	
	@Resource
	private BiHuManageService biHuManageService;

	@Override
	protected Result<TenantConfig> execute(Request request, Administrator operator) {
		return Result.result(biHuManageService.tenantConfig(request.getParam(Params.TID)));
	}
}
