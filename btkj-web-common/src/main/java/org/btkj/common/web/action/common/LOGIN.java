package org.btkj.common.web.action.common;

import org.btkj.common.service.ParamUtil;
import org.btkj.common.web.Beans;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.common.web.pojo.info.LoginInfo;
import org.btkj.pojo.model.CaptchaReceiver.Type;
import org.btkj.web.util.Params;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.UserLoginModel;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录：如果用户不存在则客户端需要跳转到代理人资格申请界面，让用户输入信息；如果用户已经存在则直接登录成功
 * 
 * @author ahab
 */
public class LOGIN extends CommonAction {

	@Override
	public Result<LoginInfo> execute(Request request) {
		CaptchaVerifier verifier = ParamUtil.captchaVerifier(request);
		Result<String> result = Beans.courierService.captchaVerify(verifier);
		if (result.getCode() == -1) 
			return Result.result(Code.CAPTCHA_ERROR);
		return _onLogin(request, verifier);
	}
	
	/**
	 * 真正的登录逻辑
	 * 
	 * @param session
	 * @param verifier
	 */
	private Result<LoginInfo> _onLogin(Request request, CaptchaVerifier verifier) { 
		Type type = verifier.getType();
		switch (type) {
		case MOBILE:
			Result<UserLoginModel> result = Beans.userService.loginWithMobile(verifier.getAppId(), verifier.getIdentity());
			if (result.getCode() == -2) {
				DistributeSession session = request.distributeSession(redis);
				session.put(Params.MOBILE.key(), verifier.getIdentity());
				session.put(Params.APP_ID.key(), String.valueOf(verifier.getAppId()));
				return Result.result(Code.USER_NOT_EXIST, result.getDesc());
			}
			if (result.getCode() == -1)
				return Result.result(Code.USER_STATUS_CHANGED);
			return Result.result(new LoginInfo(result.attach().getToken(), result.attach().getUser()));
		default:
			throw new UnsupportedOperationException("Illegal login type!");
		}
	}
}
