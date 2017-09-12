package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

public class LOGOUT extends Action<NilParam> {
	
	@Resource
	private LoginService loginService;

	@Override
	protected Result<?> execute(NilParam param) {
		return loginService.logout(client(), request().getHeader(Params.TOKEN));
	}
}
