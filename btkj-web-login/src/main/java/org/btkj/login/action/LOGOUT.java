package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

public class LOGOUT extends Action {
	
	@Resource
	private LoginService loginService;

	@Override
	public Result<?> execute(Request request) {
		return loginService.logout(client(request), request.getHeader(Params.TOKEN));
	}
}
