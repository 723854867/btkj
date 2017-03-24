package org.btkj.common.web.action.common;

import org.btkj.common.web.Request;
import org.btkj.common.web.action.Action;
import org.btkj.pojo.model.Credential;
import org.rapid.util.common.message.Result;

/**
 * 招募验证：调用该接口之前必须先调用获取验证码的接口
 * 
 * @author ahab
 */
public class RECRUIT_AUTH extends Action {

//	@Override
//	public Result<?> execute(Request request) {
//		InviterModel inviter = request.getParam(CommonParams.CREDENTIAL);
//		CaptchaVerifier verifier = ParamUtil.captchaVerifier(request);
//		Result<String> result = courierService.captchaVerify(verifier);
//		if (result.getCode() == -1) 
//			return Result.result(Code.CAPTCHA_ERROR);
//		
//		switch (verifier.getType()) {
//		case MOBILE:
//				
//			break;
//		default:
//			throw ConstConvertFailureException.errorConstException(Params.TYPE);
//		}
//		return null;
//	}

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		// TODO Auto-generated method stub
		return null;
	}
}
