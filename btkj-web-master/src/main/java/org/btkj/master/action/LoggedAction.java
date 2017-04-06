package org.btkj.master.action;

import org.btkj.master.service.Role;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public abstract class LoggedAction extends MasterAction {

	@Override
	public final Result<?> execute(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Role role = roleService.getByToken(token);
		if (null == role)
			return Result.result(Code.TOKEN_INVALID);
		return execute(request, role);
	}
	
	protected abstract Result<?> execute(Request request, Role role);
}
