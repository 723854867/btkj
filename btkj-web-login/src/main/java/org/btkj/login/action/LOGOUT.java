package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

public class LOGOUT implements Action {
	
	@Resource
	private LoginService loginService;

	@Override
	public Result<?> execute(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Client client = request.getParam(Params.CLIENT);
		return loginService.logout(client, token);
	}
}
