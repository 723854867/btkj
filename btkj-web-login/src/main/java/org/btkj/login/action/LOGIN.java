package org.btkj.login.action;

import org.btkj.login.Beans;
import org.btkj.pojo.info.mainpage.AppMainPageInfo;
import org.btkj.pojo.info.mainpage.ILoginInfo;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OpenAction;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录模块
 * 
 * @author ahab
 */
public class LOGIN extends OpenAction implements Beans {

	@Override
	protected Result<ILoginInfo> execute(Request request, Credential credential) {
		switch (credential.getClientType()) {
		case PC:
			return loginService.pcLogin(credential.getApp(), request.getParam(Params.MOBILE), request.getParam(Params.PWD));
		case MANAGER:
			return loginService.managerLogin(credential.getApp(), request.getParam(Params.MOBILE), request.getParam(Params.PWD));
		default: // app
			CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, credential);
			if (courierService.captchaVerify(verifier).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			
			Result<ILoginInfo> result = loginService.appLogin(credential.getApp(), credential.getTenant(), verifier.getIdentity());
			if (result.getCode() == Code.USER_NOT_EXIST.id()) {
				DistributeSession session = request.distributeSession(redis);
				session.put(Params.MOBILE.key(), verifier.getIdentity());
				session.put(Params.APP_ID.key(), String.valueOf(verifier.getAppId()));
				return result;
			}
			if (!result.isSuccess())
				return result;
			ParamsUtil.fillTenantInfos((AppMainPageInfo) result.attach());
			return result;
		}
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.APP.type() | ClientType.MANAGER.type() | ClientType.PC.type();
	}
}
