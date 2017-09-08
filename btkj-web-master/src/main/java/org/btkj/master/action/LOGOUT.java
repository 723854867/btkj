package org.btkj.master.action;

import org.btkj.master.AdminAction;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.NilParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;

public class LOGOUT extends AdminAction<NilParam> {

	@Override
	protected Result<Void> execute(Admin admin, NilParam param) {
		cloudService.logout(request().getHeader(Params.TOKEN));
		return Result.success();
	}
}
