package org.btkj.common.web.action.common;

import org.btkj.common.web.Request;
import org.btkj.common.web.action.Action;
import org.btkj.pojo.model.Credential;
import org.rapid.util.common.message.Result;

/**
 * 根据邀请码获取邀请人信息：可以是某个用户也可以是某家代理公司
 * 
 * @author ahab
 */
public class INVITER_INFO extends Action {

//	@Override
//	public Result<InviterInfo> execute(Request request) {
//		InviterModel inviter = request.getParam(CommonParams.CREDENTIAL);
//		return Result.result(new InviterInfo(inviter));
//	}

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		// TODO Auto-generated method stub
		return null;
	}
}
