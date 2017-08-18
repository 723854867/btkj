package org.btkj.login.action.app;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.identity.App;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldAppAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 重置密码
 * 
 * @author ahab
 */
public class PWD_RESET extends OldAppAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, App app) {
		String pwd = request.getParam(Params.PWD);
		String mobile = request.getParam(Params.MOBILE);
		if (courierService.captchaVerify(app.getId(), mobile, request.getParam(Params.CAPTCHA)).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return userService.pwdReset(app.getId(), mobile, pwd);
	}
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
