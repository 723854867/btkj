package org.btkj.login.action;

import org.btkj.login.Beans;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.enums.CredentialSegment;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN extends TenantAction implements Beans {

	@Override
	protected Result<String> execute(Request request, Credential credential) {
		CaptchaReceiver receiver = ParamsUtil.captchaReceiver(request, credential);
		Result<String> result = courierService.captchaObtain(receiver);
		if (result.getCode() == -1)
			result.setCode(Code.CAPTCHA_GET_CD.id());
		if (result.getCode() == -2)
			result.setCode(Code.CAPTCHA_COUNT_LIMIT.id());
		return result;
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.APP.type();
	}
	
	/**
	 * 只需要 appId 即可以了
	 */
	@Override
	protected int credentialMod(Credential credential) {
		return CredentialSegment.appGradeSegmentMod();
	}
}
