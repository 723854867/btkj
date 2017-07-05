package org.btkj.master.action.vehicle.bihu;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class BI_HU_TENANT_CONFIG_ADD extends AdministratorAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = tenantService.getTenantById(tid);
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		biHuConfigService.addTenantConfig(tid, request.getParam(Params.AGENT), request.getParam(Params.KEY));
		return Consts.RESULT.OK;
	}
}
