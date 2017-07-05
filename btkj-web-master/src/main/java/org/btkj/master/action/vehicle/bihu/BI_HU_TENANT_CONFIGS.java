package org.btkj.master.action.vehicle.bihu;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.model.Pager;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class BI_HU_TENANT_CONFIGS extends AdministratorAction {
	
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<Pager<TenantConfig>> execute(Request request, Administrator operator) {
		return biHuConfigService.tenantConfigs(request.getParam(Params.PAGE), request.getParam(Params.PAGE_SIZE));
	}
}
