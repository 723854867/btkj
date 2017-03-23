package org.btkj.common.web.action.common;

import org.btkj.common.CommonParams;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 申请加入租户
 * 
 * @author ahab
 */
public class APPLY extends CommonAction {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		Credential ucode = request.getOptionalParam(CommonParams.U_CODE);
		// 只能申请同一个 app 下的代理公司
		if (ucode.getApp().getId() != credential.getApp().getId())
			return Result.result(Code.FORBID);
		int tid = 0;
		int chief = 0;
		try {
			tid = ucode.getTenant().getTid();
			chief = null == ucode ? 0 : ucode.getUser().getUid();
		} catch (NullPointerException e) {
			throw ConstConvertFailureException.errorConstException(CommonParams.CREDENTIAL);
		}
		String token = request.getOptionalHeader(Params.TOKEN);
		if (null != token) {
			if(!BtkjUtil.isBaoTuApp(credential))					// 非保途 app 只能加申请新用户
				return Result.result(Code.USER_ALREADY_EXIST);
			btkjUserService.apply(token, tid, chief);
		}
		// 申请之前必须走登录接口往 session 中录入信息
		if (!request.containsHeader(Params.SESSION_ID))
			return Result.result(Code.FORBID);
		
		DistributeSession session = request.distributeSession(redis);
		Integer appId = session.getAndDelInt(Params.APP_ID.key());
		if (null == appId || credential.getApp().getId() != appId)
			return Result.result(Code.FORBID);
		String mobile = session.getAndDel(Params.MOBILE.key());
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		
		if (BtkjUtil.isBaoTuApp(credential))
			btkjUserService.apply(tid, mobile, name, identity, chief);
		else 
			isolateUserService.apply(appId, tid, mobile, name, identity, chief);
		return null;
	}
}
