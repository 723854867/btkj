package org.btkj.common.web.action.common;

import org.btkj.common.CommonParams;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.common.web.pojo.info.InviterInfo;
import org.btkj.pojo.model.InviterModel;
import org.rapid.util.common.message.Result;

/**
 * 根据邀请码获取邀请人信息：可以是某个用户也可以是某家代理公司
 * 
 * @author ahab
 */
public class INVITER_INFO extends CommonAction {

	@Override
	public Result<InviterInfo> execute(Request request) {
		InviterModel inviter = request.getParam(CommonParams.INVITER);
		return Result.result(new InviterInfo(inviter));
	}
}
