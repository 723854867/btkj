package org.btkj.web.util;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.mainpage.AppBtkjMainPageInfo;
import org.btkj.pojo.info.mainpage.AppMainPageInfo;
import org.btkj.pojo.info.tips.BannerTips;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.cache.BannerCache;
import org.btkj.web.util.cache.RegionCache;
import org.btkj.web.util.cache.TenantCache;
import org.rapid.util.common.cache.Cache;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.Const;
import org.rapid.util.exception.ConstConvertFailureException;

public class ParamsUtil {
	
	private static UserService userService;
	private static EmployeeService employeeService;
	private static CacheService<Cache<?, ?>> cacheService;
	
	public static final CaptchaReceiver captchaReceiver(Request request, Credential credential) {
		String mobile = request.getParam(Params.MOBILE);
		CaptchaReceiver receiver = new CaptchaReceiver();
		receiver.setAppId(credential.getApp().getId());
		receiver.setIdentity(mobile);
		return receiver;
	}
	
	public static final CaptchaVerifier captchaVerifier(Request request, Credential credential) {
		String mobile = request.getParam(Params.MOBILE);
		String captcha = request.getParam(Params.CAPTCHA);
		CaptchaVerifier verifier = new CaptchaVerifier();
		verifier.setAppId(credential.getApp().getId());
		verifier.setIdentity(mobile);
		verifier.setCaptcha(captcha);
		return verifier;
	}
	
	/**
	 * 根据识别码识别出 app、tenant、user信息：识别码的识别规则是：(clientType)(appId)(tenantId)(uid)
	 * 其中  clientType 固定为一位，appId 固定为三位，tenantId 固定为四位，uid 为正数，clientType 和 appId
	 * 必须，如果有 uid 则必须传 tenantId，如果 tenant 可以为null 则 tenantId 为固定值 "0000",
	 * 
	 * @param code
	 * @return
	 */
	public static final Credential parse(Const<?> constant, String code, boolean hasClientType) {
		StringBuilder builder = new StringBuilder(code);
		int index = 0;
		ClientType ct = null;
		if (hasClientType) 
			ct = ClientType.match(Integer.valueOf(builder.substring(index, index += BtkjConsts.CLIENT_TYPE_BIT_NUM)));
		App app = cacheService.getById(BtkjTables.APP.name(), Integer.valueOf(builder.substring(index, index += BtkjConsts.APP_ID_BIT_NUM)));
		Tenant tenant = null;
		if (BtkjUtil.isBaoTuApp(app)) {
			if (index == code.length())
				return new Credential(ct, app);
			String tid = builder.substring(index, index += BtkjConsts.TENANT_ID_BIT_NUM);
			tenant = BtkjUtil.hasTenant(tid) ? _getBaoTuTenant(constant, Integer.valueOf(tid)) : null;
			if (index == code.length())
				return new Credential(ct, app, tenant);
		} else {
			tenant = _getIsolateTenant(constant, app);
			if (index == code.length())
				return new Credential(ct, app, tenant);
		}
		
		User user = userService.getUser(Integer.valueOf(builder.substring(index)));
		if (user.getAppId() != app.getId())
			throw ConstConvertFailureException.errorConstException(constant);
		Employee employee = null;
		if (null != tenant) {
			employee = employeeService.getEmplyee(user.getUid(), tenant.getTid());
			if (null == employee)
				throw ConstConvertFailureException.errorConstException(constant);
		}
		return new Credential(ct, app, tenant, user, employee);
	}
	
	private static final Tenant _getBaoTuTenant(Const<?> constant, int tid) throws ConstConvertFailureException {
		TenantCache cache = (TenantCache) cacheService.getCache(BtkjTables.TENANT.name());
		Tenant tenant = cache.getBaotuTenant(tid);
		if (null == tenant)
			throw ConstConvertFailureException.errorConstException(constant);
		return tenant;
	}
	
	private static final Tenant _getIsolateTenant(Const<?> constant, App app) { 
		TenantCache cache = (TenantCache) cacheService.getCache(BtkjTables.TENANT.name());
		Tenant tenant = cache.getIsolateTenant(app.getId());
		if (null == tenant)
			throw ConstConvertFailureException.errorConstException(constant);
		return tenant;
	}
	
	public static final void fillTenantInfos(AppMainPageInfo pageInfo) { 
		MainTenantTips mainTenant = pageInfo.getTenant();
		if (null != mainTenant) 
			_fillMainTenant(pageInfo);
		if (pageInfo instanceof AppBtkjMainPageInfo) 
			_fillBtkjTenantList((AppBtkjMainPageInfo) pageInfo);
	}
	
	private static final void _fillMainTenant(AppMainPageInfo pageInfo) { 
		MainTenantTips mainTenant = pageInfo.getTenant();
		if (null != mainTenant) {
			Tenant tenant = cacheService.getById(BtkjTables.TENANT.name(), mainTenant.getTid());
			mainTenant.setName(tenant.getName());
			RegionCache rc = (RegionCache) cacheService.getCache(BtkjTables.REGION.name());
			Region region = rc.getById(tenant.getRegionId());
			mainTenant.setRegion(region.getName());
			BannerCache cache = (BannerCache) cacheService.getCache(BtkjTables.BANNER.name());
			List<Banner> banners = cache.getTenantBanners(mainTenant.getTid());
			if (null != banners) {
				List<BannerTips> tips = new ArrayList<BannerTips>();
				for (Banner banner : banners)
					tips.add(new BannerTips(banner));
				mainTenant.setBannerList(tips);
			}
		}
	}
	
	private static final void _fillBtkjTenantList(AppBtkjMainPageInfo pageInfo) {
		TenantCache cache = (TenantCache) cacheService.getCache(BtkjTables.TENANT.name());
		_fillTenantTips(pageInfo.getOwnTenants(), cache);
		_fillTenantTips(pageInfo.getAuditTenants(), cache);
	}
	
	private static final void _fillTenantTips(List<TenantTips> list, TenantCache cache) { 
		if (null != list && !list.isEmpty()) {
			for (TenantTips tips : list) {
				Tenant tenant = cache.getById(tips.getTid());
				tips.setName(tenant.getName());
			}
		}
	}
	
	public static void setUserService(UserService userService) {
		ParamsUtil.userService = userService;
	}
	
	public static void setEmployeeService(EmployeeService employeeService) {
		ParamsUtil.employeeService = employeeService;
	}
	
	public static void setCacheService(CacheService<Cache<?, ?>> cacheService) {
		ParamsUtil.cacheService = cacheService;
	}
}
