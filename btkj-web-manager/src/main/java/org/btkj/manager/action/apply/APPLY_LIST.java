package org.btkj.manager.action.apply;

import org.btkj.manager.Beans;
import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends TenantAction {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		int page = request.getOptionalParam(Params.PAGE);
		int pageSize = request.getOptionalParam(Params.PAGE_SIZE);
		return Beans.tenantService.applyList(credential.getTenant().getTid(), page, pageSize);
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.MANAGER.type();
	}
}
