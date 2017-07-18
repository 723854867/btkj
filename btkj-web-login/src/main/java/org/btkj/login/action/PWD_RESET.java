package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.bo.CaptchaVerifier;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 重置密码
 * 
 * @author ahab
 */
public class PWD_RESET extends AppAction {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, Client client, AppPO app) {
		String pwd = request.getParam(Params.PWD);
		CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, app.getId());
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return userService.pwdReset(app.getId(), verifier.getIdentity(), pwd);
	}
	
	@Override
	protected Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
