package org.btkj.master.action.impl;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.master.action.LoggedAction;
import org.btkj.master.service.Role;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.cache.RegionCache;
import org.btkj.web.util.cache.TenantCache;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.region.RegionUtil;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 添加代理公司
 * 
 * @author ahab
 */
public class TENANT_ADD extends LoggedAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private CacheService<?> cacheService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, Role role) {
		RegionCache regionCache = (RegionCache) cacheService.getCache(BtkjTables.REGION.name());
		Region region = regionCache.getById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		App app = cacheService.getById(BtkjTables.APP.name(), request.getParam(Params.APP_ID));
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		Region appRegion = regionCache.getById(app.getRegion());
		if (!RegionUtil.isSubRegion(appRegion.getId(), region.getId())) 
			return Result.result(Code.REGION_OUT_OF_BOUNDARY);
		TenantCache tenantCache = (TenantCache) cacheService.getCache(BtkjTables.TENANT.name());
		int num = tenantCache.tenantsNum(app.getId());
		if (0 > app.getMaxTenantsCount() && num > app.getMaxTenantsCount())					// 判断租户数是否达到限制
			return Result.result(BtkjCode.APP_TENANTS_NUM_MAX);
		
		String tenantName = request.getParam(Params.TENANT_NAME);
		String pwd = request.getParam(Params.PWD);
		int uid = request.getOptionalParam(Params.UID);
		if (0 != uid)    // 设置老用户为该代理公司的顶级用户
			return tenantService.tenantAdd(app, region, tenantName, pwd, uid);
		else {
			CaptchaVerifier verifier = captchaVerifier(request, 0);
			if (courierService.captchaVerify(verifier).getCode() == -1)
				return Result.result(Code.CAPTCHA_ERROR);
			String name = request.getParam(Params.NAME);
			String identity = request.getParam(Params.IDENTITY);
			return tenantService.tenantAdd(app, region, tenantName, name, verifier.getIdentity(), identity, pwd);
		}
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
