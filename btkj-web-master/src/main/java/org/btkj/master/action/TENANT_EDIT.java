package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Tenant;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class TENANT_EDIT extends AdministratorAction {
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		Tenant tenant = request.getParam(Params.TENANT_INFO);
		return tenantService.tenantEdit(tenant);
	}
}
