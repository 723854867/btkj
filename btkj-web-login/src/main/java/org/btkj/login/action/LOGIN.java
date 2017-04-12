package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.user.api.LoginService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 登录模块
 * 
 * @author ahab
 */
public class LOGIN implements Action {
	
	@Resource
	private Redis redis;
	@Resource
	private LoginService loginService;
	@Resource
	private CacheService<?> cacheService;
	@Resource
	private CourierService courierService;
	
	@Override
	public Result<LoginInfo> execute(Request request) {
		Client client = request.getParam(Params.CLIENT);
		switch (client) {
		case PC:
		case MANAGER:
			int tid = request.getParam(Params.TID);
			Tenant tenant = 0 >= tid ? null : cacheService.getById(BtkjTables.TENANT.name(), tid);
			if (null == tenant)
				return Result.result(BtkjCode.TENANT_NOT_EXIST);
			App app = cacheService.getById(BtkjTables.APP.name(), tenant.getAppId());
			return loginService.browserLogin(client, app, tenant, request.getParam(Params.MOBILE), request.getParam(Params.PWD));
		default:
			int appId = request.getParam(Params.APP_ID);
			app = 0 >= appId ? null : cacheService.getById(BtkjTables.APP.name(), appId);
			if (null == app)
				return Result.result(BtkjCode.APP_NOT_EXIST);
			CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, appId);
			if (courierService.captchaVerify(verifier).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			Result<LoginInfo> result = loginService.appLogin(app, verifier.getIdentity());
			if (result.getCode() == Code.USER_NOT_EXIST.id()) {
				DistributeSession session = request.distributeSession(redis);
				session.put(Params.MOBILE.key(), verifier.getIdentity());
				session.put(Params.APP_ID.key(), String.valueOf(verifier.getAppId()));
				return result;
			}
			return result;
		}
	}
}
