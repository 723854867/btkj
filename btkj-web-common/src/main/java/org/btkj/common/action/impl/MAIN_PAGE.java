package org.btkj.common.action.impl;

import org.btkj.common.Beans;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.enums.CredentialSegment;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.ResultUtil;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MAIN_PAGE extends TenantAction {
	
	@Override
	protected Result<?> execute(Request request, Credential credential) {
		String token = request.getHeader(Params.TOKEN);
		Result<AppMainPageInfo> result = Beans.userService.mainPage(credential.getApp(), credential.getTenant(), token);
		if (!result.isSuccess())
			return result;
		ResultUtil.fillMainPageInfo(result.attach());
		return result;
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.APP.type();
	}
	
	@Override
	protected int credentialMod(Credential credential) {
		return CredentialSegment.appGradeSegmentMod();
	}
}
