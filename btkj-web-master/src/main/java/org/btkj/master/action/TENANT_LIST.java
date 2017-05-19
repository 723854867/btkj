package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.info.TenantListPc;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.TenantSearcher;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class TENANT_LIST extends AdministratorAction {
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<Pager<TenantListPc>> execute(Request request, Administrator administrator) {
		TenantSearcher searcher = request.getParam(Params.TENANT_SEARCHER);
		return tenantService.tenantList(searcher);
	}
}
