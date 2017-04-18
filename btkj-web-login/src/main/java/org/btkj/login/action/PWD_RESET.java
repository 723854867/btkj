package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 重置密码
 * 
 * @author ahab
 */
public class PWD_RESET implements Action {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private CourierService courierService;

	@Override
	public Result<?> execute(Request request) {
		String pwd = request.getParam(Params.PWD);
		int appId = request.getParam(Params.APP_ID);
		CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, appId);
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return userService.pwdReset(appId, verifier.getIdentity(), pwd);
	}
}
