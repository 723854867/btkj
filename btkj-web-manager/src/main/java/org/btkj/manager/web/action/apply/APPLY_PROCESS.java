package org.btkj.manager.web.action.apply;

import org.btkj.manager.web.Request;
import org.btkj.manager.web.action.Action;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;

public class APPLY_PROCESS extends Action {

	@Override
	public Result<?> execute(Request session) {
		return tenantService.applyProcess(
				session.getParam(Params.TID), 
				session.getParam(Params.APPLY_KEY), 
				session.getOptionalParam(Params.AGREE));
	}
}
