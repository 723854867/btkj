package org.btkj.manager.action.apply;

import org.btkj.manager.Beans;
import org.btkj.manager.action.ManagerAction;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends ManagerAction {

	@Override
	protected Result<?> execute(Request request, Credential credential, User operator) {
		int page = request.getOptionalParam(Params.PAGE);
		int pageSize = request.getOptionalParam(Params.PAGE_SIZE);
		return Beans.tenantService.applyList(credential.getTenant().getTid(), page, pageSize);
	}
}
