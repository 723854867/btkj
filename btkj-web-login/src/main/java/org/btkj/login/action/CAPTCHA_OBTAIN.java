package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.CaptchaReceiver;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN implements Action {
	
	@Resource
	private AppService appService;
	@Resource
	private CourierService courierService;

	@Override
	public Result<String> execute(Request request) {
		int appId = request.getParam(Params.APP_ID);
		if (0 != appId && null == appService.getAppById(appId))
			return Result.result(BtkjCode.APP_NOT_EXIST);
		CaptchaReceiver receiver = ParamsUtil.captchaReceiver(request, appId);
		Result<String> result = courierService.captchaObtain(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
}
