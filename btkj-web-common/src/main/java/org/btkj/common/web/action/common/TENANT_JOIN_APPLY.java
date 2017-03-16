package org.btkj.common.web.action.common;

import org.btkj.common.CommonParams;
import org.btkj.common.web.Beans;
import org.btkj.common.web.Request;
import org.btkj.common.web.action.CommonAction;
import org.btkj.common.web.pojo.info.LoginInfo;
import org.btkj.pojo.model.InviterModel;
import org.btkj.pojo.model.TenantJoinApplyType;
import org.btkj.web.util.BtkjUtil;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 申请加入租户
 * 
 * @author ahab
 */
public class TENANT_JOIN_APPLY extends CommonAction {

	@Override
	public Result<LoginInfo> execute(Request request) {
		InviterModel inviter = request.getParam(CommonParams.INVITER);
		if (BtkjUtil.isBaoTuApp(inviter.getApp().getId())) {
			TenantJoinApplyType type = TenantJoinApplyType.match(request.getOptionalParam(Params.TYPE));
			if (null == type)
				throw ConstConvertFailureException.errorConstException(Params.TYPE);
			if (type == TenantJoinApplyType.MEMBER) {
				Beans.userService.memberJoinTenantApply(inviter, request.getParam(Params.MOBILE));
				return null;
			}
		} 
		String identity = request.getParam(Params.IDENTITY);
		String name = request.getParam(Params.NAME);
		Beans.userService.touristJoinTenantApply(inviter, request.getParam(Params.TOKEN), name, identity);
		return null;
	}
}
