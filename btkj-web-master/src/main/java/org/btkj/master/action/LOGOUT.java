package org.btkj.master.action;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;

public class LOGOUT extends AdminAction<Param> {

	@Override
	protected Result<Void> execute(Administrator admin, Param param) {
		cloudService.logout(request().getHeader(Params.TOKEN));
		return Result.success();
	}
}
