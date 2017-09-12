package org.btkj.login.action.app;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.courier.api.CourierService;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.param.user.LoginParam;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录模块
 * 
 * @author ahab
 */
public class LOGIN extends AppAction<LoginParam> {
	
	@Resource
	private LoginService loginService;
	@Resource
	private CourierService courierService;
	
	@Override
	protected Result<?> execute(AppPO app, LoginParam param) {
		switch (client()) {
		case APP:
		case RECRUIT:
			if (null == param.getCaptcha())
				return Consts.RESULT.FORBID;
			String mobile = param.getMobile();
			if (courierService.captchaVerify(app.getId(), mobile, param.getCaptcha()).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			return loginService.login(client(), app, mobile);
		case TENANT_MANAGER:
			if (null == param.getPwd())
				return Consts.RESULT.FORBID;
			return loginService.login(app, param.getMobile(), DigestUtils.md5Hex(param.getPwd()));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
