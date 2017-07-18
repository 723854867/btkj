package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.bo.CaptchaVerifier;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录模块
 * 
 * @author ahab
 */
public class LOGIN extends AppAction {
	
	@Resource
	private LoginService loginService;
	@Resource
	private CourierService courierService;
	
	@Override
	protected Result<?> execute(Request request, Client client, AppPO app) {
		switch (client) {
		case TENANT_MANAGER:
			return loginService.login(app, request.getParam(Params.MOBILE), request.getParam(Params.PWD));
		case APP:
			CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, app.getId());			// app 登录需要验证码
			if (courierService.captchaVerify(verifier).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			return loginService.login(app, client, verifier.getIdentity());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
