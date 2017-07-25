package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class PLATFORM_TENANT_SET extends UserAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(Request request, User user) {
		String name = request.getOptionalParam(Params.NAME);
		String licenseBack = request.getOptionalParam(Params.IDENTITY_FACE);
		String licenseFace = request.getOptionalParam(Params.IDENTITY_BACK);
		return null;
	}
}
