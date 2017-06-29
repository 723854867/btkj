package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.master.api.MasterService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class TENANT_ADD extends PlatformAction {
	
	@Resource
	private AppService appService;
	@Resource
	private UserService userService;
	@Resource
	private MasterService masterService;
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigService configService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, App app, User operator) {
		// 获取验证代理公司信息
		String tname = request.getParam(Params.TNAME);
		Region region = configService.getRegionById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		// 代理公司的行政区域必须是平台行政区域的子行政区域
		if (!configService.isSubRegion(app.getRegion(), region.getId()))
			return Consts.RESULT.REGION_OUT_OF_BOUNDARY;
		
		String mobile = request.getParam(Params.MOBILE);
		User user = userService.getUser(mobile, app.getId());
		if (null == user) 
			return Consts.RESULT.USER_NOT_EXIST;
		if (null == user.getName())				// 资料不齐的用户不能作为商户顶级雇员
			return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
		
		CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, mobile, app.getId());	
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Consts.RESULT.CAPTCHA_ERROR;
		if (app.isTenantAddAutonomy())
			return tenantService.tenantAdd(app, region, tname, user);
		else {
			int tenantNum = appService.tenantNum(app);
			if (0 < app.getMaxTenantsCount() && tenantNum >= app.getMaxTenantsCount()) 
				return Result.result(BtkjCode.APP_TENANT_NUM_MAXIMUM);
			if (userService.tenantNumMax(user)) 
				return Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
			masterService.tenantAdd(app, region, tname, user);
		}
		return Result.success();
	}
}
