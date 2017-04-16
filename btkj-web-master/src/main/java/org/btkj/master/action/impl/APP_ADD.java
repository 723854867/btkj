package org.btkj.master.action.impl;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.master.action.LoggedAction;
import org.btkj.master.service.Role;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.info.AppCreateInfo;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.region.RegionUtil;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 添加 app - 必须指定第一个租户和 root 用户
 * 
 * @author ahab
 */
public class APP_ADD extends LoggedAction {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigService  configService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<AppCreateInfo> execute(Request request, Role role) {
		Region region = configService.getRegionById(request.getParam(Params.REGION));
		Region tenantRegion = configService.getRegionById(request.getParam(Params.TENANT_REGION));
		if (null == region || null == tenantRegion)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		if (!RegionUtil.isSubRegion(region.getId(), tenantRegion.getId()))
			return Result.result(Code.REGION_OUT_OF_BOUNDARY);
		
		String appName = request.getParam(Params.APP_NAME);
		int maxTenantsCount = request.getParam(Params.MAX_TENANTS_COUNT);
		String tenantName = request.getParam(Params.TENANT_NAME);
		String pwd = request.getParam(Params.PWD);
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		CaptchaVerifier verifier = captchaVerifier(request);
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return appService.addApp(region.getId(), appName, maxTenantsCount, tenantRegion.getId(), tenantName, pwd, verifier.getIdentity(), name, identity);
	}

	private CaptchaVerifier captchaVerifier(Request request) {
		String mobile = request.getParam(Params.MOBILE);
		String captcha = request.getParam(Params.CAPTCHA);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(0);
		verifier.setIdentity(mobile);
		verifier.setCaptcha(captcha);
		return verifier;
	}
}
