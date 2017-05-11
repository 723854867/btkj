package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APPLY_PROCESS extends TenantAction {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<?> execute(Request request, App app, Tenant tenant, User user) {
		return tenantService.applyProcess(tenant.getTid(), request.getParam(Params.UID), request.getOptionalParam(Params.AGREE));
	}
}
