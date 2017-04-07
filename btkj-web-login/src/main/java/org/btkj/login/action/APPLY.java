package org.btkj.login.action;

import org.btkj.login.Beans;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.enums.CredentialSegment;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 申请加入租户
 * 
 * @author ahab
 */
public class APPLY extends TenantAction implements Beans {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		Credential ucode = request.getParam(Params.UCODE);
		// 只能申请同一个 app 下的代理公司
		if (ucode.getApp().getId() != credential.getApp().getId())
			return Result.result(Code.FORBID);
		ParamsUtil.checkCredential(Params.UCODE, ucode, CredentialSegment.inviteCodeMod());
		String token = request.getOptionalHeader(Params.TOKEN);
		if (null != token) {
			if(!BtkjUtil.isBaoTuApp(credential))					// 非保途 app 只能加申请新用户
				return Result.result(Code.USER_ALREADY_EXIST);
			return loginService.apply(token, ucode.getTenant(), ucode.getUser());
		}
		// 申请之前必须走登录接口往 session 中录入信息
		String sessionId = request.getHeader(Params.SESSION_ID);
		if (null == sessionId)
			return Result.result(Code.FORBID);
		
		DistributeSession session = request.distributeSession(redis, sessionId);
		Integer appId = session.getAndDelInt(Params.APP_ID.key());
		if (null == appId || credential.getApp().getId() != appId)
			return Result.result(Code.FORBID);
		String mobile = session.getAndDel(Params.MOBILE.key());
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		return loginService.apply(credential.getApp(), credential.getTenant(), mobile, name, identity, ucode.getUser());
	}
	
	@Override
	protected int credentialMod(Credential credential) {
		return CredentialSegment.tenantGradeSegmentMod();
	}
}
