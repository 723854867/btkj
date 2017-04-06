package org.btkj.master.action.impl;

import org.btkj.master.action.LoggedAction;
import org.btkj.master.service.Role;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.cache.RegionCache;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 添加代理商
 * 
 * @author ahab
 */
public class TENANT_ADD extends LoggedAction {

	@Override
	protected Result<?> execute(Request request, Role role) {
		Region region = ((RegionCache) cacheService.getCache(BtkjTables.REGION.name())).getById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		
		String tenantName = request.getParam(Params.TENANT_NAME);
		String pwd = request.getParam(Params.PWD);
		int uid = request.getOptionalParam(Params.UID);
		// 设置保途用户为顶级用户
		if (0 != uid) 									
			return tenantService.tenantAdd(region, tenantName, pwd, uid);
		// 设置一个新用户为顶级用户：需要验证手机号码
		CaptchaVerifier verifier = captchaVerifier(request, 0);
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		String appName = request.getOptionalParam(Params.APP_NAME);
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		return tenantService.tenantAdd(region, appName, tenantName, name, verifier.getIdentity(), identity, pwd);
	}
	
	private CaptchaVerifier captchaVerifier(Request request, int appId) {
		String mobile = request.getParam(Params.MOBILE);
		String captcha = request.getParam(Params.CAPTCHA);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(appId);
		verifier.setIdentity(mobile);
		verifier.setCaptcha(captcha);
		return verifier;
	}
}
