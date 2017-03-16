package org.btkj.common.web.action.common;

import org.btkj.common.CommonParams;
import org.btkj.common.web.Beans;
import org.btkj.common.web.action.CommonAction;
import org.btkj.common.web.pojo.info.LoginInfo;
import org.btkj.common.web.session.UserSession;
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
	public Result<LoginInfo> execute(UserSession session) {
		InviterModel inviter = session.getParam(CommonParams.INVITER);
		if (BtkjUtil.isBaoTuApp(inviter.getApp().getId())) {
			TenantJoinApplyType type = TenantJoinApplyType.match(session.getOptionalParam(Params.TYPE));
			if (null == type)
				throw ConstConvertFailureException.errorConstException(Params.TYPE);
			if (type == TenantJoinApplyType.MEMBER) {
				Beans.userService.memberJoinTenantApply(inviter, session.getParam(Params.MOBILE));
				return null;
			}
		} 
		String identity = session.getParam(Params.IDENTITY);
		String name = session.getParam(Params.NAME);
		Beans.userService.touristJoinTenantApply(inviter, session.getParam(Params.TOKEN), name, identity);
		return null;
	}
}
