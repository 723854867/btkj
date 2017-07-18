package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.CaptchaVerifier;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class TENANT_ADD extends UserAction {
	
	@Resource
	private AppService appService;
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigService configService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, User user) {
		String tname = request.getParam(Params.TNAME);
		int region = request.getParam(Params.REGION);
		if (!configService.isSubRegion(user.region(), request.getParam(Params.REGION)))
			return Consts.RESULT.REGION_OUT_OF_BOUNDARY;
		String licenseFace = request.getParam(Params.IDENTITY_FACE);
		String licenseBack = request.getParam(Params.IDENTITY_BACK);
		String mobile = request.getParam(Params.MOBILE);
		String servicePhone = request.getParam(Params.SERVICE_PHONE);
		User target = userService.user(mobile, user.getAppId());
		if (null == target) 
			return Consts.RESULT.USER_NOT_EXIST;
		if (null == target.getName())				// 资料不齐的用户不能作为商户顶级雇员
			return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
		
		CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, mobile, user.getAppId());	
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Consts.RESULT.CAPTCHA_ERROR;
		return tenantService.tenantAdd(user.getAppId(), target.getUid(), region, tname, licenseFace, licenseBack, servicePhone);
	}
}
