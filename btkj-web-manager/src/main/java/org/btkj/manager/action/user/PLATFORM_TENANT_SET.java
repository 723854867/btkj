package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;

public class PLATFORM_TENANT_SET extends UserAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(Request request, User user) {
		String name = request.getParam(Params.NAME);
		String contactsMobile = request.getParam(Params.CONTACTS_MOBILE);
		String tname = request.getOptionalParam(Params.TNAME);
		String license = request.getOptionalParam(Params.IDENTITY);
		String licenseImage = request.getOptionalParam(Params.IDENTITY_FACE);
		int expire = request.getOptionalParam(Params.END_TIME);
		if (expire <= DateUtil.currentTime())
			throw ConstConvertFailureException.errorConstException(Params.END_TIME);
		return userManageService.tenantSet(user, request.getParam(Params.TID), name, contactsMobile, tname, license, licenseImage, expire);
	}
}
