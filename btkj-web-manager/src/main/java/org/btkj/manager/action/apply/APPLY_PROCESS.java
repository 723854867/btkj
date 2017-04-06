package org.btkj.manager.action.apply;

import org.btkj.manager.Beans;
import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class APPLY_PROCESS extends TenantAction {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		return Beans.tenantService.applyProcess(
				credential.getTenant().getTid(), 
				request.getParam(Params.APPLY_KEY), 
				request.getOptionalParam(Params.AGREE));
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.MANAGER.type();
	}
}
