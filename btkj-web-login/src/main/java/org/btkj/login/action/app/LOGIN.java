package org.btkj.login.action.app;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.model.identity.App;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldAppAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录模块
 * 
 * @author ahab
 */
public class LOGIN extends OldAppAction {
	
	@Resource
	private LoginService loginService;
	@Resource
	private CourierService courierService;
	
	@Override
	protected Result<?> execute(Request request, App app) {
		switch (app.getClient()) {
		case TENANT_MANAGER:
			return loginService.login(app, request.getParam(Params.MOBILE), request.getParam(Params.PWD));
		case APP:
			String mobile = request.getParam(Params.MOBILE);
			if (courierService.captchaVerify(app.getId(), mobile, request.getParam(Params.CAPTCHA)).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			return loginService.login(app, mobile);
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
