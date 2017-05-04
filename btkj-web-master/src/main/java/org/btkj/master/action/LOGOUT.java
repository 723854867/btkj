package org.btkj.master.action;

import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class LOGOUT extends AdministratorAction {

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		cloudService.logout(request.getHeader(Params.TOKEN));
		return Result.success();
	}
}
