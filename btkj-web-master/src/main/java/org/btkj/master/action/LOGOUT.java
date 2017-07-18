package org.btkj.master.action;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class LOGOUT extends LoggedAction {

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		cloudService.logout(request.getHeader(Params.TOKEN));
		return Result.success();
	}
}
