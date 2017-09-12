package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.login.pojo.param.CaptchaObtainParam;
import org.btkj.pojo.BtkjCode;
import org.btkj.user.api.AppService;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN extends Action<CaptchaObtainParam> {
	
	@Resource
	private AppService appService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<String> execute(CaptchaObtainParam param) {
		if (0 != param.getAppId() && null == appService.app(param.getAppId()))
			return Result.result(BtkjCode.APP_NOT_EXIST);
		Result<String> result = courierService.captchaObtain(param.getAppId(), param.getMobile());
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
}
