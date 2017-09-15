package org.btkj.login.action.app;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.courier.api.CourierService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.user.LoginParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 重置密码
 * 
 * @author ahab
 */
public class PWD_RESET extends AppAction<LoginParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(App app, LoginParam param) {
		if (null == param.getCaptcha() || null == param.getPwd())
			return Consts.RESULT.FORBID;
		if (courierService.captchaVerify(app.getId(), param.getMobile(), param.getCaptcha()).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return userService.pwdReset(app.getId(), param.getMobile(), DigestUtils.md5Hex(param.getPwd()));
	}
	
	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
