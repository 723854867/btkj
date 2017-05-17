package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.enums.TenantState;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class TENANT_STATE extends AdministratorAction {
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		int tid = request.getParam(Params.TID);
		TenantState state = request.getParam(Params.TENANT_STATE);
		return tenantService.tenantState(tid, state);
	}
}
