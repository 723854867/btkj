package org.btkj.common.web.action.common;

import java.io.Serializable;

import org.btkj.common.service.ParamUtil;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.model.CaptchaReceiver.Type;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
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
	public Result<Serializable> execute(Request request, Credential credential) {
		CaptchaVerifier verifier = ParamUtil.captchaVerifier(request, credential);
		Result<String> result = courierService.captchaVerify(verifier);
		if (result.getCode() == -1) 
			return Result.result(Code.CAPTCHA_ERROR);
		return _onLogin(request, verifier, credential);
	}
	
	/**
	 * 真正的登录逻辑
	 * 
	 * @param session
	 * @param verifier
	 */
	private Result<Serializable> _onLogin(Request request, CaptchaVerifier verifier, Credential credential) { 
		Type type = verifier.getType();
		switch (type) {
		case MOBILE:
			Result<Serializable> result = null;
			if (BtkjUtil.isBaoTuApp(credential))
				result = btkjUserService.loginWithMobile(verifier.getIdentity());
			else 
				result = isolateUserService.loginWithMobile(credential.getApp().getId(), credential.getTenant().getTid(), verifier.getIdentity());
			// 缓存手机和 app 信息，下次直接用 sessionId 登录即可
			if (result.getCode() == Code.USER_NOT_EXIST.id()) {
				DistributeSession session = request.distributeSession(redis);
				session.put(Params.MOBILE.key(), verifier.getIdentity());
				session.put(Params.APP_ID.key(), String.valueOf(verifier.getAppId()));
				return result;
			}
			return result;
		default:
			throw new UnsupportedOperationException("Illegal login type!");
		}
	}
}
