package org.btkj.common.web.action.common;

import org.btkj.common.CommonParams;
import org.btkj.common.service.ParamUtil;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.InviterModel;
import org.btkj.web.util.Params;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 招募验证：调用该接口之前必须先调用获取验证码的接口
 * 
 * @author ahab
 */
public class RECRUIT_AUTH extends CommonAction {

	@Override
	public Result<?> execute(Request request) {
		InviterModel inviter = request.getParam(CommonParams.INVITER);
		CaptchaVerifier verifier = ParamUtil.captchaVerifier(request);
		Result<String> result = courierService.captchaVerify(verifier);
		if (result.getCode() == -1) 
			return Result.result(Code.CAPTCHA_ERROR);
		
		switch (verifier.getType()) {
		case MOBILE:
				
			break;
		default:
			throw ConstConvertFailureException.errorConstException(Params.TYPE);
		}
		return null;
	}
}
